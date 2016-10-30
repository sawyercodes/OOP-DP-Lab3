import java.util.Observer;

public interface ShannonsController {

	public void addObserver(Observer o);
	
	public void setBandwidth(double bandwidth);
	
	public void setSignalToNoise(double signalToNoiseRatio);

}
