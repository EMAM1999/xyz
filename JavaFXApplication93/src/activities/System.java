/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package activities;
import javafx.scene.Group;

/**

 @author EMAM
 */
public abstract class System {

      protected double F;

      public double getF() {
            return F;
      }

      public void setF(double _F) {
            this.F = _F;
      }

      public abstract Group draw();
}
