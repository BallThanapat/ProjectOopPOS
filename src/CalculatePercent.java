public class CalculatePercent extends CalculateProfit{
    protected double pcpf ;
    @Override
    public void setPercentM(){
        pcpf = ((getProfit())*100)/totalMprice1;
    }
    public double getPcpf(){
        return pcpf;
    }
}
