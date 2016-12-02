package longteng.pda.utils;

/**
 * 子线程更新UI
 *
 * @author qinzhy
 * @date 2015年6月8日
 */
public abstract class ThreadUpdateUI {

	private Object[] mParameters;

	/**
	 * 获取传入参数
	 *
	 * @return
	 */
	public Object[] getParameters() {
		return mParameters;
	}

	/**
	 * 构造方法
	 *
	 * @param parameters
	 *            传入参数
	 */
	public ThreadUpdateUI(Object... parameters) {
		this.mParameters = parameters;
	}

	/**
	 * 构造方法
	 */
	public ThreadUpdateUI() {

	}

	/**
	 * 子线程更新UI写这里
	 */
	public abstract void doUpdate();
}
