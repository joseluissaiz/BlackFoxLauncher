package com.overshade.blackfoxlauncher.Games.PegSolitaire;

import android.app.AlertDialog;
import android.app.Dialog;
import android.view.View;
import android.widget.ImageView;

import com.overshade.blackfoxlauncher.R;

import java.util.IllegalFormatCodePointException;

public class PegSolitaireGame {
    private View gameView;
    private Peg[][] pegs;
    private Peg selection = null;

    public PegSolitaireGame(View gameView) { this.gameView = gameView;}


    // GAME LOGIC

    private boolean gameIsLosed() {
        boolean canContinue = false;
        for (int x = 0; x < pegs.length; x++) {
            for (int y = 0; y < pegs[0].length; y++) {
                if (pegs[x][y].isAccesible() &&
                        (pegs[x][y].getState() == Peg.PegState.UNSELECTED ||
                                pegs[x][y].getState() == Peg.PegState.SELECTED)) {
                    for (int x2 = 0; x2 < pegs.length; x2++) {
                        for (int y2 = 0; y2 < pegs[0].length; y2++) {
                            if (canMoveToPeg(pegs[x][y], pegs[x2][y2])) {
                                canContinue = true;
                                System.out.println("CAN CONTINUE PLAYING");
                                return false;
                            }
                        }
                    }
                }
            }
        }
        System.out.println("GAME OVER - LOSED");
        return true;
    }

    public boolean canMoveToPeg(Peg selection, Peg objective) {
        if (objective == null) {
            System.out.println("No se puede mover");
            return false;
        }

        System.out.println("Selection: {"+selection.getX()+","+selection.getY()+"}");
        System.out.println("Objective: {"+objective.getX()+","+objective.getY()+"}");
        System.out.println("ObBoard  : {"+pegs[objective.getX()][objective.getY()].getX()+","+pegs[objective.getX()][objective.getY()].getY()+"}");

        //For Y axis
        if (objective.getY()+2 == selection.getY() && objective.getX() == selection.getX()) {
            if (objective.getState() == Peg.PegState.HIDED) {
                if (pegs[objective.getX()][objective.getY()+1].getState() == Peg.PegState.UNSELECTED) {
                    System.out.println("Se puede mover");
                    return true;
                }
            }
        }

        if (objective.getY()-2 == selection.getY() && objective.getX() == selection.getX()) {
            if (objective.getState() == Peg.PegState.HIDED) {
                if (pegs[objective.getX()][objective.getY()-1].getState() == Peg.PegState.UNSELECTED) {
                    System.out.println("Se puede mover");
                    return true;
                }
            }
        }

        //For X axis
        if (objective.getX()+2 == selection.getX() && objective.getY() == selection.getY()) {
            if (objective.getState() == Peg.PegState.HIDED) {
                if (pegs[objective.getX()+1][objective.getY()].getState() == Peg.PegState.UNSELECTED) {
                    System.out.println("Se puede mover");
                    return true;
                }
            }
        }

        if (objective.getX()-2 == selection.getX() && objective.getY() == selection.getY()) {
            if (objective.getState() == Peg.PegState.HIDED) {
                if (pegs[objective.getX()-1][objective.getY()].getState() == Peg.PegState.UNSELECTED) {
                    System.out.println("Se puede mover");
                    return true;
                }
            }
        }

        System.out.println("No se puede mover");
        return false;
    }

    public void moveToPeg(Peg pegObjective) {
        if (pegObjective.getX() - 2 == selection.getX()) {
            pegs[pegObjective.getX()-1][pegObjective.getY()].changeState(Peg.PegState.HIDED);
        }
        if (pegObjective.getX() + 2 == selection.getX()) {
            pegs[pegObjective.getX()+1][pegObjective.getY()].changeState(Peg.PegState.HIDED);
        }
        if (pegObjective.getY() - 2 == selection.getY()) {
            pegs[pegObjective.getX()][pegObjective.getY()-1].changeState(Peg.PegState.HIDED);
        }
        if (pegObjective.getY() + 2 == selection.getY()) {
            pegs[pegObjective.getX()][pegObjective.getY()+1].changeState(Peg.PegState.HIDED);
        }
        selection.changeState(Peg.PegState.HIDED);
        pegObjective.changeState(Peg.PegState.UNSELECTED);
        selection = null;
        if (gameIsLosed()) {
            Dialog dialog = new Dialog(gameView.getContext());
            dialog.setTitle("GAME LOST");
            dialog.create();
            dialog.show();
        }
    }

    //GAME INITIALIZER

    public void restart() {
        assignViewToVariables();
        createFirstHole();
    }

    private void createFirstHole() {
        pegs[3][3].changeState(Peg.PegState.HIDED);
    }

    private void assignViewToVariables() {
        pegs = new Peg[7][7];
        pegs[0][0] = new Peg(this, 0, 0, gameView.findViewById(R.id.f0c0), false);
        pegs[1][0] = new Peg(this, 1, 0, gameView.findViewById(R.id.f0c1), false);
        pegs[2][0] = new Peg(this, 2, 0, gameView.findViewById(R.id.f0c2), true);
        pegs[3][0] = new Peg(this, 3, 0, gameView.findViewById(R.id.f0c3), true);
        pegs[4][0] = new Peg(this, 4, 0, gameView.findViewById(R.id.f0c4), true);
        pegs[5][0] = new Peg(this, 5, 0, gameView.findViewById(R.id.f0c5), false);
        pegs[6][0] = new Peg(this, 6, 0, gameView.findViewById(R.id.f0c6), false);

        pegs[0][1] = new Peg(this, 0, 1, gameView.findViewById(R.id.f1c0), false);
        pegs[1][1] = new Peg(this, 1, 1, gameView.findViewById(R.id.f1c1), false);
        pegs[2][1] = new Peg(this, 2, 1, gameView.findViewById(R.id.f1c2), true);
        pegs[3][1] = new Peg(this, 3, 1, gameView.findViewById(R.id.f1c3), true);
        pegs[4][1] = new Peg(this, 4, 1, gameView.findViewById(R.id.f1c4), true);
        pegs[5][1] = new Peg(this, 5, 1, gameView.findViewById(R.id.f1c5), false);
        pegs[6][1] = new Peg(this, 6, 1, gameView.findViewById(R.id.f1c6), false);

        pegs[0][2] = new Peg(this, 0, 2, gameView.findViewById(R.id.f2c0), true);
        pegs[1][2] = new Peg(this, 1, 2, gameView.findViewById(R.id.f2c1), true);
        pegs[2][2] = new Peg(this, 2, 2, gameView.findViewById(R.id.f2c2), true);
        pegs[3][2] = new Peg(this, 3, 2, gameView.findViewById(R.id.f2c3), true);
        pegs[4][2] = new Peg(this, 4, 2, gameView.findViewById(R.id.f2c4), true);
        pegs[5][2] = new Peg(this, 5, 2, gameView.findViewById(R.id.f2c5), true);
        pegs[6][2] = new Peg(this, 6, 2, gameView.findViewById(R.id.f2c6), true);

        pegs[0][3] = new Peg(this, 0, 3, gameView.findViewById(R.id.f3c0), true);
        pegs[1][3] = new Peg(this, 1, 3, gameView.findViewById(R.id.f3c1), true);
        pegs[2][3] = new Peg(this, 2, 3, gameView.findViewById(R.id.f3c2), true);
        pegs[3][3] = new Peg(this, 3, 3, gameView.findViewById(R.id.f3c3), true);
        pegs[4][3] = new Peg(this, 4, 3, gameView.findViewById(R.id.f3c4), true);
        pegs[5][3] = new Peg(this, 5, 3, gameView.findViewById(R.id.f3c5), true);
        pegs[6][3] = new Peg(this, 6, 3, gameView.findViewById(R.id.f3c6), true);

        pegs[0][4] = new Peg(this, 0, 4, gameView.findViewById(R.id.f4c0), true);
        pegs[1][4] = new Peg(this, 1, 4, gameView.findViewById(R.id.f4c1), true);
        pegs[2][4] = new Peg(this, 2, 4, gameView.findViewById(R.id.f4c2), true);
        pegs[3][4] = new Peg(this, 3, 4, gameView.findViewById(R.id.f4c3), true);
        pegs[4][4] = new Peg(this, 4, 4, gameView.findViewById(R.id.f4c4), true);
        pegs[5][4] = new Peg(this, 5, 4, gameView.findViewById(R.id.f4c5), true);
        pegs[6][4] = new Peg(this, 6, 4, gameView.findViewById(R.id.f4c6), true);

        pegs[0][5] = new Peg(this, 0, 5, gameView.findViewById(R.id.f5c0), false);
        pegs[1][5] = new Peg(this, 1, 5, gameView.findViewById(R.id.f5c1), false);
        pegs[2][5] = new Peg(this, 2, 5, gameView.findViewById(R.id.f5c2), true);
        pegs[3][5] = new Peg(this, 3, 5, gameView.findViewById(R.id.f5c3), true);
        pegs[4][5] = new Peg(this, 4, 5, gameView.findViewById(R.id.f5c4), true);
        pegs[5][5] = new Peg(this, 5, 5, gameView.findViewById(R.id.f5c5), false);
        pegs[6][5] = new Peg(this, 6, 5, gameView.findViewById(R.id.f5c6), false);

        pegs[0][6] = new Peg(this, 0, 6, gameView.findViewById(R.id.f6c0), false);
        pegs[1][6] = new Peg(this, 1, 6, gameView.findViewById(R.id.f6c1), false);
        pegs[2][6] = new Peg(this, 2, 6, gameView.findViewById(R.id.f6c2), true);
        pegs[3][6] = new Peg(this, 3, 6, gameView.findViewById(R.id.f6c3), true);
        pegs[4][6] = new Peg(this, 4, 6, gameView.findViewById(R.id.f6c4), true);
        pegs[5][6] = new Peg(this, 5, 6, gameView.findViewById(R.id.f6c5), false);
        pegs[6][6] = new Peg(this, 6, 6, gameView.findViewById(R.id.f6c6), false);

    }

    // DTO

    public Peg getSelection() { return selection; }

    public void setSelection(Peg selection) { this.selection = selection; }

}
