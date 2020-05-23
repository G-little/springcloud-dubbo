package com.little.g.springcloud.admin.dto;

import java.util.List;

public class UserResourceDTO extends ResourcesDTO {

	private boolean checked;

	private List<UserResourceDTO> subMenus;

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public List<UserResourceDTO> getSubMenus() {
		return subMenus;
	}

	public void setSubMenus(List<UserResourceDTO> subMenus) {
		this.subMenus = subMenus;
	}

}
