package it.bz.idm.bdp.dal.charger;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.ManyToOne;
import javax.persistence.TypedQuery;

import it.bz.idm.bdp.dal.BasicData;
import it.bz.idm.bdp.dal.Station;
import it.bz.idm.bdp.dal.util.JPAUtil;

@Entity
public class EChargingPlugBasicData extends BasicData{

	@ManyToOne
	private EChargingStation eStation;


	public EChargingStation geteStation() {
		return eStation;
	}

	public void seteStation(EChargingStation eStation) {
		this.eStation = eStation;
	}


	public static List<EChargingPlugBasicData> findAllPlugs(
			EntityManager em) {
		TypedQuery<EChargingPlugBasicData> query = em.createQuery("Select basic from EChargingPlugBasicData basic where basic.station.active=true", EChargingPlugBasicData.class);
		return query.getResultList();
	}

	@Override
	public BasicData findByStation(EntityManager em, Station station) {
		TypedQuery<EChargingPlugBasicData> typedQuery = em.createQuery("select basic from EChargingPlugBasicData basic where basic.station = :station and basic.station.active=:active",EChargingPlugBasicData.class);
		typedQuery.setParameter("station", station);
		typedQuery.setParameter("active",true);
		return JPAUtil.getSingleResultOrNull(typedQuery);
	}


}
