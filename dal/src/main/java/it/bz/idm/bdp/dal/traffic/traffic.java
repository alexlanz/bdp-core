package it.bz.idm.bdp.dal.traffic;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EntityManager;

import it.bz.idm.bdp.dal.DataType;
import it.bz.idm.bdp.dal.Station;
import it.bz.idm.bdp.dal.authentication.BDPRole;
import it.bz.idm.bdp.dto.RecordDto;
import it.bz.idm.bdp.dto.StationDto;
import it.bz.idm.bdp.dto.TypeDto;

@Entity
public class traffic extends Station{

	@Override
	public List<String[]> findDataTypes(EntityManager em, String stationId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TypeDto> findTypes(EntityManager em, String stationId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object pushRecords(EntityManager em, Object... object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void sync(EntityManager em, Station station, StationDto dto) {
		// TODO Auto-generated method stub

	}

	@Override
	public Date getDateOfLastRecord(EntityManager em, Station station, DataType type, Integer period, BDPRole role) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RecordDto findLastRecord(EntityManager em, String cname, Integer period, BDPRole role) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RecordDto> getRecords(EntityManager em, String type, Date start, Date end, Integer period,
			BDPRole role) {
		// TODO Auto-generated method stub
		return null;
	}

}
