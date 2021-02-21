import controller.Control;
import controller.ControlGUI;
import model.Log;
import view.SwingUI;
import view.TextUI;

public class WellnessManager {
   public static void main(String[] args) {
      Log l = new Log();
//      TextUI tui = new TextUI(l);
      SwingUI sui = new SwingUI(l);
//      Control c = new Control(l, tui);

      ControlGUI c = new ControlGUI(l, sui);
      c.initController();
//      c.execute();

   }
}
