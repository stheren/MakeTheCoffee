import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class CoffeeGrinder {
    private Robot grinder;
    private ArrayList<Bean> BagBean;

    private Runnable  PressFordward = () -> grinder.keyPress(KeyEvent.VK_SHIFT);
    private Runnable  ReleaseFordward = () -> grinder.keyRelease(KeyEvent.VK_SHIFT);

    private Runnable  PressBackward = () -> grinder.keyPress(KeyEvent.VK_CONTROL);
    private Runnable  ReleaseBackward = () -> grinder.keyRelease(KeyEvent.VK_CONTROL);

    private Runnable  PressLeft = () -> grinder.keyPress(KeyEvent.VK_LEFT);
    private Runnable  ReleaseLeft = () -> grinder.keyRelease(KeyEvent.VK_LEFT);

    private Runnable  PressRight = () -> grinder.keyPress(KeyEvent.VK_RIGHT);
    private Runnable  ReleaseRight = () -> grinder.keyRelease(KeyEvent.VK_RIGHT);

    private Runnable PressDelete = () -> grinder.keyPress(KeyEvent.VK_DELETE);
    private Runnable ReleaseDelete = () -> grinder.keyRelease(KeyEvent.VK_DELETE);

    private Runnable PressRestart = () -> grinder.keyPress(KeyEvent.VK_ENTER);
    private Runnable ReleaseRestart = () -> grinder.keyRelease(KeyEvent.VK_ENTER);

    private static void setTimeout(Runnable runnable, int delay){
        new Thread(() -> {
            try {
                Thread.sleep(delay);
                runnable.run();
            }
            catch (Exception e){
                System.err.println(e);
            }
        }).start();
    }

    private void setPressed(Bean Grain){
        switch (Grain.Parfum){
            case Bean.DELETE:
                setTimeout(PressDelete, Grain.Start);
                setTimeout(ReleaseDelete, Grain.Stop);
                break;
            case Bean.RESTART:
                setTimeout(PressRestart, Grain.Start);
                setTimeout(ReleaseRestart, Grain.Stop);
                break;
            case Bean.LEFT:
                setTimeout(PressLeft, Grain.Start);
                setTimeout(ReleaseLeft, Grain.Stop);
                break;
            case Bean.RIGHT:
                setTimeout(PressRight, Grain.Start);
                setTimeout(ReleaseRight, Grain.Stop);
                break;
            case Bean.FORWARD:
                setTimeout(PressFordward, Grain.Start);
                setTimeout(ReleaseFordward, Grain.Stop);
                break;
            case Bean.BACKWARD:
                setTimeout(PressBackward, Grain.Start);
                setTimeout(ReleaseBackward, Grain.Stop);
                break;
            default:
        }
    }

    public CoffeeGrinder(ArrayList<Bean> BagBean) throws AWTException {
        grinder = new Robot();
        this.BagBean = BagBean;
    }

    public void grind() {
        for (Bean Grain : BagBean) {
            setPressed(Grain);
        }
    }
}
