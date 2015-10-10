/**
 * Copyright (C) 2012 skymobi LTD
 *
 * Licensed under GNU GENERAL PUBLIC LICENSE  Version 3 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.gnu.org/licenses/gpl-3.0.html
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.appleframework.monitor.security;


import org.jasig.cas.client.model.User;

/**
 * Author: Hill.Hu
 *
 * @see org.springframework.security.core.userdetails.User
 */
public class LogsUser extends User {

	private static final long serialVersionUID = -4200755293028586878L;

	public boolean isAdmin() {
		if(isadmin == 1)
			return true;
		else
			return false;
	}
	
	public boolean isAuthenticated() {
		return true;
	}
	
}
