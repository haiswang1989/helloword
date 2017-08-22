package com.hiworld.lintcode.medium;

/**
 * 问题：硬币排成线
 * 描述：有 n 个硬币排成一条线。两个参赛者轮流从右边依次拿走 1 或 2 个硬币，直到没有硬币为止。拿到最后一枚硬币的人获胜。
 * 请判定 第一个玩家 是输还是赢？
 * 
 * 样例：
 * n = 1, 返回 true.
 * n = 2, 返回 true.
 * n = 3, 返回 false.
 * n = 4, 返回 true.
 * n = 5, 返回 true.
 * 
 * 
 * 挑战：O(1) 时间复杂度且O(1) 存储。
 * 
 * 
 * @author Administrator
 *
 */
public class CoinFirstWinI {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		CoinFirstWinI coinFirstWinI = new CoinFirstWinI();
		boolean win = coinFirstWinI.firstWillWin(3);
		System.out.println("win : " + win);
	}
	
	/**
	 * 观察规律：
	 * 
	 * 对于n为1 那么第一个人可以直接拿走 胜利
	 * 对于n为2 那么第一个人可以直接拿走 胜利
	 * 对于n为3不管第一个人拿1还是2,那么第二个人可以拿2或者1 获胜
	 * 
	 * 也就是如果是n是3的倍数,那么第一个人就会输
	 * 不管第一个人怎么拿,如果拿1那么第二个人拿2或者拿2第二个人拿1,那么凑出一个3,这样就保证3的倍数就是第二个人胜利 
	 *
	 * 
     * @param n: an integer
     * @return: a boolean which equals to true if the first player will win
     */
    public boolean firstWillWin(int n) {
        // write your code here
    	return n%3 != 0;
    }

}
