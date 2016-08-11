public class ExperimentHQ {

	public static void main(String[] args) {
		int trials = 10000;
		double dfs_suc = 0.0, bfs_suc = 0.0, rc_suc = 0.0, drc_suc = 0.0;
		double prob_suc = 0.0, apr0_suc = 0.0, apr1_suc = 0.0;
		double pathlen_dfs = 0.0, pathlen_bfs = 0.0, pathlen_rc = 0.0, pathlen_drc = 0.0;
		double pathlen_prob = 0.0, pathlen_apr0 = 0.0, pathlen_apr1 = 0.0;
		RoutingHQ val = new RoutingHQ(10, 0.1);
		val.setNeighbors();
		for(int i=0; i<trials; i++){
			val.setFault();
			val.setSrcDst();
			if(val.bfs(val.getSrcNode(), val.getDstNode(), val.getFault()) == 1){
				//System.out.println("Source Node :      " + Integer.toBinaryString(val.getSrcNode()));
				//System.out.println("Destination Node : " + Integer.toBinaryString(val.getDstNode()));
				//System.out.println("Distance : " + val.getDistance(val.getSrcNode(), val.getDstNode()));
				bfs_suc++;
				pathlen_bfs += val.pathlen_bfs;
				if(val.dfs(val.getSrcNode(), val.getDstNode(), val.getFault()) == 1){
					dfs_suc++;
					pathlen_dfs += val.pathlen_dfs;
				}

				val.setRC(val.getSize(), val.getDim(), val.getNeighbors(), val.getFault());
				if(val.route0(val.getSrcNode(), val.getDstNode(), val.getFault()) == 1){
					rc_suc++;
					pathlen_rc += val.pathlen_rc;
				}
				val.setDRC(val.getSize(), val.getDim(), val.getNeighbors(), val.getFault());
				if(val.route1(val.getSrcNode(), val.getDstNode(), val.getFault()) == 1){
					drc_suc++;
					pathlen_drc += val.pathlen_drc;
				}
				val.setProb(val.getSize(), val.getDim(), val.getNeighbors(), val.getFault());
				if(val.pb_routing(val.getSrcNode(), val.getDstNode(), val.getFault()) == 1){
					prob_suc++;
					pathlen_prob += val.pathlen_prob;
				}
				val.setARP(val.getSize(), val.getDim(), val.getNeighbors(), val.getFault());
				if(val.dk0(val.getSrcNode(), val.getDstNode(), val.getFault()) == 1){
					apr0_suc++;
					pathlen_apr0 += val.pathlen_apr0;
				}
				if(val.dk1(val.getSrcNode(), val.getSrcNode(), val.getDstNode(), val.getFault()) == 1){
					apr1_suc++;
					pathlen_apr1 += val.pathlen_apr1;
				}
			}
			else i--;
		}
		System.out.println("Ratio of Reachability BFS : " + 100 * bfs_suc / trials + "[%]");
		System.out.println("Average Path Length BFS : " + pathlen_bfs / bfs_suc);
		System.out.println("Ratio of Reachability DFS : " + 100 * dfs_suc / trials + "[%]");
		System.out.println("Average Path Length DFS : " + pathlen_dfs / dfs_suc);
		System.out.println("Ratio of Reachability RC : " + 100 * rc_suc / trials + "[%]");
		System.out.println("Average Path Length RC : " + pathlen_rc / rc_suc);
		System.out.println("Ratio of Reachability DRC : " + 100 * drc_suc / trials + "[%]");
		System.out.println("Average Path Length DRC : " + pathlen_drc / drc_suc);
		System.out.println("Ratio of Reachability PB : " + 100 * prob_suc / trials + "[%]");
		System.out.println("Average Path Length PB : " + pathlen_prob / prob_suc);
		System.out.println("Ratio of Reachability DK0 : " + 100 * apr0_suc / trials + "[%]");
		System.out.println("Average Path Length DK0 : " + pathlen_apr0 / apr0_suc);
		System.out.println("Ratio of Reachability DK1 : " + 100 * apr1_suc / trials + "[%]");
		System.out.println("Average Path Length DK1 : " + pathlen_apr1 / apr1_suc);
	}

}