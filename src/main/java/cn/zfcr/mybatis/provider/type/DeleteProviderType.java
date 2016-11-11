package cn.zfcr.mybatis.provider.type;

public class DeleteProviderType<T> {

	public String delete(long id){
		return "delete from City where id = '"+id+"'";
	}
}
