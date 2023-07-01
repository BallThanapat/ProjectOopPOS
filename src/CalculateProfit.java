public abstract class CalculateProfit implements Financial{
    protected double totalMcost1;
    protected double totalMprice1;
    protected double totalProfit;
    @Override
    public void totalProfit(){
        totalProfit = totalMprice1-totalMcost1;
    }
    public void setTotalmcost(int totalMcost){
        this.totalMcost1 = totalMcost;
    }
    public void setTotalmcost(double totalMcost){
        this.totalMcost1 = totalMcost;
    }
    public void setTotalmprice(int totalMprice){
        this.totalMprice1 =  totalMprice;
    }
    public double getProfit(){
        return totalProfit;
    }
    @Override
    public abstract void setPercentM();
}
