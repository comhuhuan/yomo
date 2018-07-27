package study.desinepattern.Template;

/**
 * <p>Title:TempTestImpl
 * <p>Description:
 * <p>Modified History:
 *
 * @author CVTE
 * @date 2018/7/27 14:25
 */
public class TempTestImpl extends TempTest {

    Boll boll;

    @Override
    public void playFootBoll() {
        boll=new FootBoll();
        boll.play();
    }

    @Override
    public void playPingPong() {
        boll=new PingPong();
        boll.play();
    }

    @Override
    public void playBaskt() {
        boll=new Baskt();
        boll.play();
    }


}