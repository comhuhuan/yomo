package yomo.study.desinepattern.Template;

/**
 * <p>Title:TempTest
 * <p>Description:模板方法
 * <p>Modified History:
 *
 * @author HH
 * @date 2018/7/27 14:21
 */
public abstract class TempTest {
    public abstract void playFootBoll();

    public abstract void playPingPong();

    public abstract void playBaskt();

    public final void play() {
        playBaskt();
        playPingPong();
        playFootBoll();
    }


    public static void main(String[] args) {
        //new App().run();
        TempTestImpl tempTest = new TempTestImpl();
        tempTest.play();
    }


}