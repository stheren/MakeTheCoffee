public class Bean {
    public final static String DELETE = "DELETE";
    public final static String RESTART = "RESTART";
    public final static String LEFT = "LEFT";
    public final static String RIGHT = "RIGHT";
    public final static String FORWARD = "FORWARD";
    public final static String BACKWARD = "BACKWARD";


    public final String Parfum;
    public final int Start;
    public final int Stop;

    public Bean(String Parfum, int Pressed, int Released) throws UnvalidParfum {
        this.Parfum = Parfum;
        this.Start = Pressed;
        this.Stop = Released;
        this.CheckParfum();
    }

    public Bean(String Parfum, int Click) throws UnvalidParfum {
        this.Parfum = Parfum;
        this.Start = Click;
        this.Stop = Click;
        this.CheckParfum();
    }

    private void CheckParfum() throws UnvalidParfum{
        switch (Parfum){
            case DELETE :
            case RESTART :
            case LEFT :
            case RIGHT :
            case FORWARD :
            case BACKWARD :
                return;
            default:
                throw new UnvalidParfum("Parfum : '" + Parfum + "' doesn't exist.");
        }
    }

    @Override
    public String toString() {
        return "Bean{" +
                "Parfum='" + Parfum + '\'' +
                ((Start!=Stop)? ", PressedAt=" :", ClickAt=") + Start +
                ((Start!=Stop)? ", ReleasedAt=" + Stop :"") +
                '}';
    }
}
