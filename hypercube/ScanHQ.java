public class ScanHQ {

	/*
	 * Hypercubeの節点間の距離算出
	 */

	private int bits;
	public ScanHQ(int bits){
		this.bits = bits;
	}
	public int getBits(){
		return bits;
	}
	public int scan(){
		return Integer.bitCount(getBits());
	}
}