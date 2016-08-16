package email;


public class CreateCode {

	public String randomCode() {
		String code = "";
		for (int i = 0; i < 6; i++) {
			int n = (int) (Math.random() * 10);
			code += n;
		}
		return code;

	}

}
