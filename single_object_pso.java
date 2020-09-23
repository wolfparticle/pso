package pso;

public class single_pso {
	int n=2;
	double[] y;
	double[] x;
	double[] v;
	double c1 = 2;
	double c2 = 2;
	double[] pbest;
	double gbest;
	double vmax=0.1;
	
	public void fitnessFunction(){
		for (int i = 0; i < 2; i++){
			y[i] = -1 * x[i]*(x[i] - 2);
		}
	}
	
	public void init(){
		x = new double[n];
		v = new double[n];
		y = new double[n];
		pbest = new double[n];
		gbest = 0.1;
		x[0] = 0.0;
		x[1] = 2.0;
		v[0] = 0.01;
		v[1] = 0.02;
		fitnessFunction();
		for(int i=0; i<n; i++){
			pbest[i] = y[i];
			if (y[i] > gbest){
				gbest = y[i];
			}
		}
		System.out.println("算法开始，起始初始化："+gbest);
		System.out.println("\n");
	}
	
	public double getMax(double a, double b){
		return a>b?a:b;
	}
	
	public void PSO(int max_iter){
		for(int t=0; t < max_iter; t++){
			double w = 0.4;
			for (int i=0; i < n; i++){
				v[i] = w * v[i] + c1*Math.random()*(pbest[i] - x[i]) + c2 * Math.random()*(gbest-x[i]);
				if(v[i] > vmax){
					v[i] = vmax;
				}
				x[i] += v[i];
				if(x[i] > 2) x[i] = 2;
				if(x[i] < 0) x[i] = 0;
			}
			fitnessFunction();
			for(int i = 0; i < n; i++){
				pbest[i] = getMax(y[i], pbest[i]);
				if(pbest[i] > gbest) gbest = pbest[i];
				System.out.println("粒子n"+i+":x = " + x[i] + " " + "v =  " + v[i]);
			}
			System.out.println("第"+(t + 1) +"次迭代，全局最优解 gbset = " + gbest);
			System.out.println("\n");
		}
	}
	public static void main(String[] args){
		single_pso ts = new single_pso();
		ts.init();
		ts.PSO(10);
	}
}

