package enum_exa;

public enum TypeEnum {
	
	FIREWALL("firewall"),SECRET("secret"),BANLANCE("blance");
	
	private String typeName;
	
	TypeEnum(String name){
		this.typeName = name;
	}
	
	/**
	 * �����������Ʒ���ö��ʵ��
	 * @return
	 */
	public static TypeEnum getFromTypeName(String typeName) {
//		if(typeName==null || typeName == "") {
//			return null;
//		}
		for(TypeEnum t:TypeEnum.values()) {
			if(t.getTypeName().equals(typeName)) {
				return t;
			}
		}
		return null;
	}
	
	public String getTypeName() {
		return this.typeName;
	}

}

