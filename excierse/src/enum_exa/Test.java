package enum_exa;

public class Test {
	public static void main(String[] args) {
		System.out.println(TypeEnum.BANLANCE.getTypeName());
		
//		String typeName = "firewall";
//	    //String typeName = "secretMac";
//	    TypeEnum typeEnum = TypeEnum.getFromTypeName(typeName);
//	    if (typeEnum == null) {
//	        return;
//	    }
//	    switch (typeEnum) {
//	        case FIREWALL:
//	            System.out.println("ö������(��Ĭ���Դ������� name ��ֵ)�ǣ�" + typeEnum.name());
//	            System.out.println("����ֵ(Ĭ���Դ������� ordinal ��ֵ)�ǣ�" + typeEnum.ordinal());
//	            System.out.println("ö�ٵ��Զ������� typeName ��ֵ�ǣ�" + typeEnum.getTypeName());
//	            break;
//	        case SECRET:
//	            System.out.println("ö������(��Ĭ���Դ������� name ��ֵ)�ǣ�" + typeEnum.name());
//	            System.out.println("����ֵ(Ĭ���Դ������� ordinal ��ֵ)�ǣ�" + typeEnum.ordinal());
//	            System.out.println("ö�ٵ��Զ������� typeName ��ֵ�ǣ�" + typeEnum.getTypeName());
//	            break;
//	        case BANLANCE:
//	            System.out.println("ö������(��Ĭ���Դ������� name ��ֵ)�ǣ�" + typeEnum.name());
//	            System.out.println("����ֵ(Ĭ���Դ������� ordinal ��ֵ)�ǣ�" + typeEnum.ordinal());
//	            System.out.println("ö�ٵ��Զ������� typeName ��ֵ�ǣ�" + typeEnum.getTypeName());
//	            break;
//	        default:
//	            System.out.println("default");
//	    }

		System.out.println(Status.DELETE.ordinal());
	}
}
