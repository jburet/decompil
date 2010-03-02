package testclasses.exception;

public class ExceptionBlock {

	public boolean simpleTryCatch() {
		try {
			int i = 0;
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	public boolean TryMultipleCatch() {
		try {
			int i = 0;
		} catch (Exception e) {
			return false;
		} catch (Throwable t) {
			return false;
		}
		return true;
	}

	public boolean simpleTryCatchFinally() {
		int i;
		try {
			i = 0;
		} catch (Exception e) {
			return false;
		} finally {
			i = 1;
		}
		return true;
	}

	public boolean imbriquedTryCatch() {
		try {
			int i = 0;
			try {
				int j = 2;
			} catch (Exception e) {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	public boolean simpleTryCatchTryInFinally() {
		int i;
		try {
			i = 0;
		} catch (Exception e) {
			return false;
		} finally {
			i = 1;
			try {
				int k = 1;
			} catch (Exception e) {
				return false;
			}
		}
		return true;
	}
}
