package cn.zfcr.common.base.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * 实体类必须继承此类 id统一使用UUID
 * @author zhangfeng
 *
 */
public class BaseEntity {

	@Id
	@GeneratedValue(generator="UUID")
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
}
