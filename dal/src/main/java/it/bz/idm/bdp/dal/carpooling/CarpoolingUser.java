/**
 * BDP data - Data Access Layer for the Big Data Platform
 * Copyright © 2018 OpenDataHub (info@opendatahub.bz.it)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program (see LICENSE/GPLv3). If not, see
 * <http://www.gnu.org/licenses/>.
 */
package it.bz.idm.bdp.dal.carpooling;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.EntityManager;

import it.bz.idm.bdp.dal.MeasurementStation;
import it.bz.idm.bdp.dal.Station;
import it.bz.idm.bdp.dto.StationDto;
import it.bz.idm.bdp.dto.carpooling.CarpoolingUserDto;
import it.bz.idm.bdp.dto.carpooling.LocationTranslationDto;

@Entity
public class CarpoolingUser extends MeasurementStation{

	
	@Override
	public List<StationDto> convertToDtos(EntityManager em, List<Station> resultList) {
		List<StationDto> dtos = new ArrayList<StationDto>();
		for (Station station: resultList){
			Double x = null,y = null;
			if (station.getPointprojection() != null){
				y = station.getPointprojection().getY();
				x = station.getPointprojection().getX();
			}
			CarpoolingUserBasicData basic = (CarpoolingUserBasicData) new CarpoolingUserBasicData().findByStation(em, station);
			if (basic == null)
				continue;
			CarpoolinghubBasicData hubBasic = (CarpoolinghubBasicData) new CarpoolinghubBasicData().findByStation(em, basic.getHub());
			Map<String, LocationTranslationDto> hubTranslation = castToDto(hubBasic.getI18n());
			Map<String, LocationTranslationDto> userTranslation = castToDto(basic.getLocation());
			CarpoolingUserDto dto = new CarpoolingUserDto(station.getStationcode(), station.getName(), y, x,
					basic.getCarType(), basic.getGender(), basic.getType(), basic.getPendular(),
					basic.getArrival(), basic.getDeparture(),basic.getHub().getStationcode(),hubTranslation,userTranslation);
			dto.setMunicipality(station.getMunicipality());
			dtos.add(dto);
		}
		return dtos;
	}

	private Map<String, LocationTranslationDto> castToDto(Map<Locale, Translation> map) {
		Map<String,LocationTranslationDto> translations = new HashMap<String,LocationTranslationDto>();
		for (Map.Entry<Locale, Translation> entry: map.entrySet()){
			if (entry.getValue() instanceof HubTranslation){
				HubTranslation translation = (HubTranslation) entry.getValue();
				translations.put(entry.getKey().toLanguageTag(), new LocationTranslationDto(translation.getName(),translation.getAddress(),translation.getCity()));
			}
			
		}
		return translations;
	}
	
	@Override
	public void sync(EntityManager em, Station station, StationDto dto) {
		if (dto instanceof CarpoolingUserDto){
			CarpoolingUserDto carpoolingUserDto = (CarpoolingUserDto) dto;
			CarpoolingUserBasicData basic = (CarpoolingUserBasicData) new CarpoolingUserBasicData().findByStation(em, station);
			Carpoolinghub hub = (Carpoolinghub) new Carpoolinghub().findStation(em, carpoolingUserDto.getHub());
			if (basic == null)
				basic = new CarpoolingUserBasicData();
			basic.setStation(station);
			basic.setPendular(carpoolingUserDto.getPendular());
			basic.setCarType(carpoolingUserDto.getCarType());
			basic.setType(carpoolingUserDto.getType());
			basic.setGender(carpoolingUserDto.getGender());
			basic.setName(carpoolingUserDto.getName());
			basic.setHub(hub);
			basic.setArrival(carpoolingUserDto.getArrival());
			basic.setDeparture(carpoolingUserDto.getDeparture());
			for (Map.Entry<String, LocationTranslationDto> entry: carpoolingUserDto.getLocation().entrySet()){
				HubTranslation translation = (HubTranslation) basic.getLocation().get(Locale.forLanguageTag(entry.getKey()));
				if (translation == null){
					translation = new HubTranslation();
					basic.getLocation().put(Locale.forLanguageTag(entry.getKey()), translation);
				}
				translation.setName(entry.getValue().getName());
				translation.setAddress(entry.getValue().getAddress());
				translation.setCity(entry.getValue().getCity());
			}
			em.merge(basic);
		}
	}

}
