/**
 * reader - Data Reader for the Big Data Platform, that queries the database for web-services
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
 * along with this program (see LICENSES/GPL-3.0.txt). If not, see
 * <http://www.gnu.org/licenses/>.
 *
 * SPDX-License-Identifier: GPL-3.0
 */
package it.bz.idm.bdp.reader;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;

import it.bz.idm.bdp.dal.util.JPAUtil;

public class JPADataSource {
	
	@Bean
	public DataSource getJpaDatasource() {
		return JPAUtil.emFactory.unwrap(DataSource.class);
	}

}
