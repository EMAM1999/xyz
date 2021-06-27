/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package activities;
import activities.Systeme_Simple.Type;

import static design.APP.*;

import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**

 @author EMAM
 */
public class LunetteAstronomique extends System {

      private double distance;

      private double F1;
      private double x0;
      private double y0;

      private double[] rays;

      String data1;
      String data2;

      public LunetteAstronomique(double _F1, double _F2) {
            this(_F1, _F2, "", "");
      }

      public LunetteAstronomique(double _F1, double _F2, String data1, String data2) {
            this.F1 = _F1;
            super.F = _F2;
            this.x0 = this.y0 = -_F1;
            distance = 500;
//            distance = 2 * Math.abs(this.F1);
            this.data1 = data1;
            this.data2 = data2;
      }

      @Override
      public Group draw() {
            Group g1 = drawParrarelLines();
            Systeme_Simple simple = new Systeme_Simple(Type.LENTILLE_CONVERGENTE, super.F, -distance + x0, y0, 2, false, "A1 B1", "A' B'");
            simple.setDataShown(this.isDataShown());
            simple.setData(data2);
            Group g2 = simple.draw();
            g2.setTranslateX(distance);
            return new Group(g1, g2);
      }

      public String[] getData() {
            return new String[] { data1, data2 };
      }

      public void setData(String _data1, String _data2) {
            this.data1 = _data1;
            this.data2 = _data2;
      }

      public double getDistance() {
            return distance;
      }

      public double getX0() {
            return x0;
      }

      public double getY0() {
            return y0;
      }

      private Group drawParrarelLines() {
            Group g = new Group();
            if ( this.isDataShown() ) {
                  Group data = drawLunetteData();
                  Group f = drawFData();
                  g.getChildren().addAll(f, data);
            }
            Group B = drawB();
            Group xy = drawAxis();
            Group f = drawF();
            Group rays = drawRays(2);
            Group A = drawA(2.5, Color.BLACK);
            g.getChildren().addAll(B, xy, rays, A, f);
            return g;
      }

      private Group drawF() {
            int q = 3;
            Line fl1 = new Line(F1, -q, F1, q);
            Line fl2 = new Line(-F1, -q, -F1, q);
            return new Group(fl1, fl2);
      }

      private Group drawAxis() {
//            Group g = new Group();
            int q = 10;

            Line x = new Line(-xEdge, 0, xEdge, 0);
            Line y = new Line(0, -getYy() / 2, 0, getYy() / 2);
            y.setStrokeWidth(5);

            Line a1 = new Line(q, getYy() / 2 - q, 0, getYy() / 2);
            a1.setStrokeWidth(5);
            Line a2 = new Line(-q, getYy() / 2 - q, 0, getYy() / 2);
            a2.setStrokeWidth(5);
            Line b1 = new Line(q, -getYy() / 2 + q, 0, -getYy() / 2);
            b1.setStrokeWidth(5);
            Line b2 = new Line(-q, -getYy() / 2 + q, 0, -getYy() / 2);
            b2.setStrokeWidth(5);

            return new Group(x, y, a1, a2, b1, b2);
      }

      private Group drawLunetteData() {
            Text data = new Text(data1);
            data.setFont(Font.font(data.getFont().getFamily(), FontWeight.BOLD, 20));
            data.setTranslateX(-(data.getLayoutBounds().getWidth() / 2));
            data.setTranslateY(-(getYy() / 2 + 50));
            return new Group(data);
      }

      private Group drawFData() {
            Label f1 = new Label("F'1");
            f1.setFont(Font.font(20));
            f1.setTranslateX(F1);
            f1.setTranslateY(10);

            Label f2 = new Label("F1");
            f2.setFont(Font.font(20));
            f2.setTranslateX(-F1);
            f2.setTranslateY(10);

            return new Group(f1, f2);
      }

      private Group drawA(double width, Color color) {
            Group g = new Group();
            Line l = new Line(x0, 0, x0, y0);
            l.setStroke(color);
            l.setStrokeWidth(width);
            g.getChildren().add(l);

            int q = 5;
            Line a = new Line(x0 + q, Math.copySign(Math.abs(y0) - q, y0), x0, y0);
            Line b = new Line(x0 - q, Math.copySign(Math.abs(y0) - q, y0), x0, y0);
            a.setStrokeWidth(width);
            a.setStroke(color);
            b.setStrokeWidth(width);
            b.setStroke(color);
            return new Group(g, a, b);
      }

      private Group drawB() {
//                y = x
//                x = F1
            double q = 3;
            Line p1 = new Line(x0, y0 - q, x0, y0 + q);
            Line p2 = new Line(x0 + q, y0, x0 - q, y0);
            return new Group(p1, p2);
      }

      private Group drawRays(int number) {
            rays = rays == null ? new double[number] : rays;
            Group g = new Group();
            g.getChildren().add(new Line(-xEdge, -yEdge, x0, y0));
            for ( int i = 0; i < number; i++ ) {
                  double q = rays[i];
                  if ( q == 0 ) {
                        q = 50 * (int)(Math.random() * 10 - 5);
                        rays[i] = q;
                  }
                  Line l = new Line(-xEdge, -yEdge + q, 0, q);
                  Line l2 = new Line(0, q, x0, y0);
                  g.getChildren().addAll(l, l2);

            }
            return g;
      }

}
