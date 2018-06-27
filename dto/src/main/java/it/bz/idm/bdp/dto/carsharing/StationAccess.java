/**
 * dto - Data Transport Objects for an object-relational mapping
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
package it.bz.idm.bdp.dto.carsharing;

import java.io.Serializable;

public class StationAccess implements Serializable{
	private static final long serialVersionUID = -6882191851531656942L;
	String locationNote;
	   String parking;

	   public String getParking()
	   {
	      return this.parking;
	   }

	   public void setParking(String parking)
	   {
	      this.parking = parking;
	   }

	   public String getLocationNote()
	   {
	      return this.locationNote;
	   }

	   public void setLocationNote(String locationNote)
	   {
	      this.locationNote = locationNote;
	   }
}
