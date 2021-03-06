package risk.controller;

import risk.model.*;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import risk.model.*;
import risk.controller.RiskPlayerPanelViewController;

// TODO: Auto-generated Javadoc
/**
 * The RiskContoller translates the user's interactions with the
 *  into actions that the  will perform that may use
 * some additional/changed data gathered in a user-interactive view.
 * 
 */
public class RiskController extends javax.swing.JFrame implements MouseListener {
	
	/** The risk. */
	RiskGameModel risk;
	

	JFrame jfmCard;
	
	JPanel jp;
	
	/** The count trade cards. */
	int countTradeCards = 0;
	
	/** The lst traded cards. */
	public List<RiskCardModel> lstTradedCards = new ArrayList<RiskCardModel>();

	/**
	 * Instantiates a new risk controller.
	 */
	public RiskController() {
		risk = new RiskGameModel();
		initComponents();
		setLocationRelativeTo(null);
		setResizable(false);
		jPanel1.repaint();
		jPanel1.addMouseListener(this);
		AttackButton.setVisible(false);

	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * 
	 */
	private void initComponents() {

		/*
		 * jPanel1 = new javax.swing.JPanel();
		 */ jPanel1 = new RiskMapPanelViewController(risk);
		 /*
		  * jPanel3 = new javax.swing.JPanel();
		  */ jPanel3 = new RiskPlayerPanelViewController(risk);
		  statusLabel = new javax.swing.JLabel();
		  AttackButton = new javax.swing.JButton();
		  EndButton = new javax.swing.JButton();
		  FortifyButton = new javax.swing.JButton();
		  CardButton = new javax.swing.JButton();

		  setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		  org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance()
				  .getContext().getResourceMap(RiskController.class);
		  setTitle(resourceMap.getString("Form.title")); // NOI18N
		  setBackground(resourceMap.getColor("Form.background")); // NOI18N
		  setForeground(resourceMap.getColor("Form.foreground")); // NOI18N
		  setName("Form"); // NOI18N

		  jPanel1.setBackground(resourceMap.getColor("jPanel1.background")); // NOI18N
		  jPanel1.setBorder(null);
		  jPanel1.setName("jPanel1"); // NOI18N
		  jPanel1.setLayout(null);

		  jPanel3.setBackground(resourceMap.getColor("jPanel3.background")); // NOI18N
		  jPanel3.setBorder(null);
		  jPanel3.setForeground(resourceMap.getColor("jPanel3.foreground")); // NOI18N
		  jPanel3.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
		  jPanel3.setName("jPanel3"); // NOI18N
		  jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

		  statusLabel.setFont(resourceMap.getFont("statusLabel.font")); // NOI18N
		  statusLabel.setForeground(resourceMap.getColor("statusLabel.foreground")); // NOI18N
		  statusLabel.setText(resourceMap.getString("statusLabel.text")); // NOI18N
		  statusLabel.setName("statusLabel"); // NOI18N
		  jPanel3.add(statusLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(132, 63, 427, -1));

		  AttackButton.setFont(resourceMap.getFont("AttackButton.font")); // NOI18N
		  AttackButton.setText(resourceMap.getString("AttackButton.text")); // NOI18N
		  AttackButton.setName("AttackButton"); // NOI18N
		  AttackButton.addMouseListener(new java.awt.event.MouseAdapter() {
			  public void mouseClicked(java.awt.event.MouseEvent evt) {
				  AttackButtonMouseClicked(evt);
			  }
		  });
		  jPanel3.add(AttackButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 10, 89, -1));

		  EndButton.setVisible(false);
		  EndButton.setFont(resourceMap.getFont("EndButton.font")); // NOI18N
		  EndButton.setText(resourceMap.getString("EndButton.text")); // NOI18N
		  EndButton.setName("EndButton"); // NOI18N
		  EndButton.addMouseListener(new java.awt.event.MouseAdapter() {
			  public void mouseClicked(java.awt.event.MouseEvent evt) {
				  EndButtonMouseClicked(evt);
			  }
		  });
		  jPanel3.add(EndButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 10, -1, -1));

		  FortifyButton.setVisible(false);
		  FortifyButton.setFont(resourceMap.getFont("FortifyButton.font")); // NOI18N
		  FortifyButton.setText(resourceMap.getString("FortifyButton.text")); // NOI18N
		  FortifyButton.setName("FortifyButton"); // NOI18N
		  FortifyButton.addMouseListener(new java.awt.event.MouseAdapter() {
			  public void mouseClicked(java.awt.event.MouseEvent evt) {
				  FortifyButtonMouseClicked(evt);
			  }
		  });
		  jPanel3.add(FortifyButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(661, 10, 90, -1));

		  CardButton.setVisible(true);
		  CardButton.setFont(resourceMap.getFont("CardButton.font")); // NOI18N
		  CardButton.setText(resourceMap.getString("CardButton.text")); // NOI18N
		  CardButton.setName("CardButton"); // NOI18N
		  CardButton.addMouseListener(new java.awt.event.MouseAdapter() {
			  public void mouseClicked(java.awt.event.MouseEvent evt) {
				  CardButtonMouseClicked(evt);
			  }
		  });
		  jPanel3.add(CardButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(438, 10, 90, -1));

		  javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		  getContentPane().setLayout(layout);
		  layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				  .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 988, Short.MAX_VALUE)
				  .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 988, Short.MAX_VALUE));
		  layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				  .addGroup(layout.createSequentialGroup()
						  .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 533,
								  javax.swing.GroupLayout.PREFERRED_SIZE)
						  .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						  .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE)));

		  pack();
	}

	/**
	 * Attack button mouse clicked.
	 *
	 * @param evt 
	 */
	private void AttackButtonMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_AttackButtonMouseClicked
		risk.setState(RiskGameModel.ATTACK);
		FortifyButton.setVisible(false);
		EndButton.setVisible(false);
		statusLabel.setText("Select a territory to attack with");
		if (AttackButton.getText().equals("Retreat")) {
			risk.setState(RiskGameModel.ACTIVE_TURN);
			risk.aTerritory = null;
			risk.dTerritory = null;
			if (risk.active == risk.defender)
				risk.active = risk.curPlayer;
			risk.defenseNum = 0;
			risk.attackNum = 0;
			AttackButton.setText("Attack");
			EndButton.setVisible(true);
			FortifyButton.setVisible(true);
			statusLabel.setText("What would you like to do?");
			jPanel1.repaint();
		}
		// AttackButton.setText("Retreat");

	}

	/**
	 * End button mouse clicked.
	 *
	 * @param
	 */
	private void EndButtonMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_EndButtonMouseClicked
		risk.nextPlayer();
		risk.setState(RiskGameModel.START_TURN);
		risk.gamePhaseActive(0, 0);
		risk.active = risk.curPlayer;
		jPanel3.repaint();
		statusLabel.setText("Recieved a bonus of " + risk.curPlayer.getNumberOfArmies());
		AttackButton.setVisible(false);
		EndButton.setVisible(false);
		FortifyButton.setVisible(false);
		risk.drawn = false;

	}

	/**
	 * Fortify button mouse clicked.
	 *
	 * @param evt
	 */
	private void FortifyButtonMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_FortifyButtonMouseClicked
		risk.setState(RiskGameModel.FORTIFY);
		statusLabel.setText("Select a country move armies from");
	}

	/**
	 * Card button mouse clicked.
	 *
	 * @param evt
	 */
	private void CardButtonMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_CardButtonMouseClicked
		risk.setState(RiskGameModel.TRADE_CARDS);
		GenerateCardPanel();
		jPanel1.repaint();
	}

	/**
	 * Show GUI.
	 */
	public static void ShowGUI() {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new RiskController().setVisible(true);
			}
		});
	}

	/** The i. */
	private int i;
	
	/** The control panel 1. */
	private JPanel controlPanel1;
	
	/** The Attack button. */
	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JButton AttackButton;
	
	/** The Card button. */
	private javax.swing.JButton CardButton;
	
	/** The End button. */
	private javax.swing.JButton EndButton;
	
	/** The Fortify button. */
	private javax.swing.JButton FortifyButton;
	/*
	 * private javax.swing.JPanel jPanel1;
	 */private RiskMapPanelViewController jPanel1;
 	/*
	  * private javax.swing.JPanel jPanel3;
	  */ private RiskPlayerPanelViewController jPanel3;
	  
  	/** The status label. */
  	private javax.swing.JLabel statusLabel;
	  // End of variables declaration//GEN-END:variables

	  /* (non-Javadoc)
  	 * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
  	 */
  	public void mouseClicked(MouseEvent me) {
		  int x = me.getX();
		  int y = me.getY();
		  String name;
		  String out;

		  if (risk.getState() == RiskGameModel.NEW_GAME) {
			  risk.gamePhaseSetup(x, y);
			  name = risk.curPlayer.getName();
			  statusLabel.setText("Place an army on a unoccupied territory");
		  }

		  if (risk.getState() == RiskGameModel.INITIAL_REINFORCE) {
			  risk.gamePhaseSetup(x, y);
			  name = risk.curPlayer.getName();
			  statusLabel.setText("Place an army on a territory you occupy");
		  }

		  if (risk.getState() == RiskGameModel.ATTACK) {
			  name = risk.curPlayer.getName();
			  if (risk.getState() == RiskGameModel.ATTACKING) {
				  AttackButton.setText("Retreat");
				  statusLabel.setText("Select an opposing territory");
			  }
			  out = risk.gamePhaseActive(x, y);
		  }

		  if (risk.getState() == RiskGameModel.START_TURN) {
			  risk.gamePhaseActive(x, y);
			  risk.active = risk.curPlayer;
			  name = risk.curPlayer.getName();
			  statusLabel.setText("Recieved a bonus of " + risk.curPlayer.getNumberOfArmies());

		  }

		  if (risk.getState() == RiskGameModel.REINFORCE) {
			  name = risk.curPlayer.getName();
			  risk.gamePhaseActive(x, y);
			  statusLabel.setText("You have " + risk.curPlayer.getNumberOfArmies() + " left to place");
			  if (risk.curPlayer.getNumberOfArmies() == 0) {
				  risk.setState(RiskGameModel.ACTIVE_TURN);
			  }

		  }

		  if (risk.getState() == RiskGameModel.ACTIVE_TURN) {
			  name = risk.curPlayer.getName();
			  statusLabel.setText("What would you like to do?");
			  EndButton.setVisible(true);
			  FortifyButton.setVisible(true);
			  AttackButton.setText("Attack");
			  AttackButton.setVisible(true);
			  if (risk.curPlayer.getCard().size() > 2)
				  CardButton.setVisible(true);
			  else
				  CardButton.setVisible(false);
		  }

		  if (risk.getState() == RiskGameModel.ATTACKING) {
			  name = risk.curPlayer.getName();
			  AttackButton.setText("Retreat");
			  statusLabel.setText("Select an opposing territory");
			  out = risk.gamePhaseActive(x, y);
			  // System.out.println(out);
		  }

		  if (risk.getState() == RiskGameModel.ATTACK_PHASE) {
			  // jInternalFrame1.setVisible(true);
			  int attackArmies = risk.aTerritory.getArmies();
			  int defenseArmies = risk.dTerritory.getArmies();
			  int numofatt = 0;
			  // If attackers turn
			  if (risk.active == risk.curPlayer) {
				  if (attackArmies > 3) {
					  if (y > 250 && y < 280) {// if in y coord
						  if (x > 420 && x < 460) // If dice one
							  numofatt = 1;
						  if (x > 480 && x < 520) // if dice two
							  numofatt = 2;
						  if (x > 540 && x < 580)
							  numofatt = 3;
					  } // in y coord
				  } // if attcking with 3
				  if (attackArmies == 3) {// if attakking with two
					  if (y > 250 && y < 280) {
						  if (x > 460 && x < 500)
							  numofatt = 1;
						  if (x > 510 && x < 550)
							  numofatt = 2;
					  } // in y coord
				  } // end if can attack with two

				  if (attackArmies == 2) {// can only attack with one
					  if (y > 250 && y < 280) {
						  if (x > 480 && x < 520)
							  numofatt = 1;
					  } // in y
				  } // end only attack with one

				  if (numofatt != 0) {// change player is num is selected
					  risk.active = risk.defender;
					  risk.setAttack(numofatt);
				  }

			  } // end attackers turn

			  // If defenders turn
			  else if (risk.active == risk.defender) {
				  if (defenseArmies > 1 && risk.attackNum > 1) {
					  if (y > 250 && y < 280) {
						  if (x > 460 && x < 500)
							  numofatt = 1;
						  if (x > 510 && x < 550)
							  numofatt = 2;
					  }
				  } else {
					  if (y > 250 && y < 280) {
						  if (x > 480 && x < 520)
							  numofatt = 1;
					  }
				  }

				  if (numofatt > 0) {
					  risk.setDefend(numofatt);
					  risk.engageBattle();
					  if (defenseArmies - risk.dTerritory.getArmies() == 1)
						  statusLabel.setText(risk.curPlayer.getName() + " has destroyed an army");
					  else if (defenseArmies - risk.dTerritory.getArmies() == 2)
						  statusLabel.setText(risk.curPlayer.getName() + " has destroyed two armies");
					  else if (attackArmies - risk.aTerritory.getArmies() == 1)
						  statusLabel.setText(risk.curPlayer.getName() + " has lost an army");
					  else if (attackArmies - risk.aTerritory.getArmies() == 2)
						  statusLabel.setText(risk.curPlayer.getName() + " has lost two armies");

					  if (risk.aTerritory.getArmies() == 1) {
						  risk.setState(RiskGameModel.ACTIVE_TURN);
						  statusLabel.setText(risk.curPlayer.getName() + " has lost the battle");
						  AttackButton.setText("Attack");
						  FortifyButton.setVisible(true);
						  EndButton.setVisible(true);
						  risk.defenseNum = 0;
						  risk.attackNum = 0;
						  risk.dTerritory = null;
						  risk.aTerritory = null;
					  }
				  }

			  } /// end if defenders turn

		  } // End attackPhase

		  if (risk.getState() == RiskGameModel.CAPTURE) {
			  statusLabel.setText("Select number of armies to move to " + risk.dTerritory.getName());
			  AttackButton.setVisible(false);
			  AttackButton.setText("Attack");
			  int max = risk.aTerritory.getArmies() - 1;
			  int min = risk.attackNum;
			  if (y > 230 && y < 257) {// if y is correct
				  if (x > 600 && x < 650)// if max
					  risk.defenseNum = risk.aTerritory.getArmies() - 1; // max
				  if (x > 520 && x < 570) { // if inc
					  if (risk.defenseNum < max)
						  risk.defenseNum++;
					  else
						  statusLabel.setText("Maximum units already selected");
				  } // end if sub
				  if (x > 440 && x < 490) {// if dec
					  if (risk.defenseNum > min)
						  risk.defenseNum--;
					  else
						  statusLabel.setText("Minimum units already selected");
				  } // end if add
				  if (x > 360 && x < 410)// min
					  risk.defenseNum = min;
			  } // end if y coord

			  if (x > 460 && x < 545) {// move has ben clicked
				  if (y > 325 && y < 355) {// then occupy the territory
					  AttackButton.setVisible(true);
					  if (risk.defenseNum == 1)
						  statusLabel.setText("1 army moved to " + risk.dTerritory.getName());
					  else
						  statusLabel.setText(risk.defenseNum + " armies moved to " + risk.dTerritory.getName());

					  EndButton.setVisible(true);
					  FortifyButton.setVisible(true);
					  risk.capture();
				  }
			  }

		  } // end capturing

		  if (risk.getState() == RiskGameModel.FORTIFY) {
			  risk.gamePhaseActive(x, y);
			  statusLabel.setText("Select a country to move armies too");

		  }

		  if (risk.getState() == RiskGameModel.FORTIFYING) {
			  risk.gamePhaseActive(x, y);
		  }

		  if (risk.getState() == RiskGameModel.FORTIFY_PHASE) {
			  int from = risk.aTerritory.getArmies();

			  if (y > 230 && y < 257) {// if y is correct
				  if (x > 600 && x < 650)// if max
					  if (risk.defenseNum == (from - 1))
						  statusLabel.setText("Maximum units already selected");
					  else
						  risk.defenseNum = (from - 1);// all but one
				  if (x > 520 && x < 570) { // if inc
					  if (risk.defenseNum < (from - 1))
						  risk.defenseNum++;
					  else
						  statusLabel.setText("Maximum units already selected");
				  } // end if inc
				  if (x > 440 && x < 490) {// if dec
					  if (risk.defenseNum > (from - 1))
						  risk.defenseNum--;
					  else
						  statusLabel.setText("Minimum units already selected");
				  } // end if add
				  if (x > 360 && x < 410)// min
					  if (risk.defenseNum == 0)
						  statusLabel.setText("Minimum units already selected");
					  else
						  risk.defenseNum = 0;
			  } // end if y coord

			  if (x > 460 && x < 545) {// move has ben clicked
				  if (y > 325 && y < 355) {// then occupy the territory
					  AttackButton.setVisible(false);
					  if (risk.defenseNum == 1)
						  statusLabel.setText("1 army moved to " + risk.dTerritory.getName());
					  else
						  statusLabel.setText(risk.defenseNum + " armies moved to " + risk.dTerritory.getName());

					  EndButton.setVisible(true);
					  FortifyButton.setVisible(false);
					  risk.aTerritory.looseArmies(risk.defenseNum);
					  risk.dTerritory.addArmies(risk.defenseNum);
					  risk.setState(RiskGameModel.ACTIVE_TURN);
				  } // end y
			  } // end x for movwe

		  } // ..fortify phase

		  if (risk.getState() == RiskGameModel.TRADE_CARDS) {
			  if (y > 350 && y < 380) {
				  if (x > 475 && x < 525) { // if exxti button pushed
					  risk.setState(RiskGameModel.ACTIVE_TURN);
					  System.out.println("exitt");
				  }
			  } // end exit

		  } // end trade cards

		  System.out.println("(" + x + ", " + y + ")");

		  jPanel1.repaint();
		  jPanel3.repaint();

	  }

	  /**
  	 * Generate card panel.
  	 */
  	public void GenerateCardPanel() {
		  lstTradedCards = new ArrayList<RiskCardModel>();

		  /* Only for testing */
		  risk.curPlayer.setCard(new RiskCardModel(1, 39));
		  risk.curPlayer.setCard(new RiskCardModel(1, 39));
		  risk.curPlayer.setCard(new RiskCardModel(3, 11));
		  /* Only for testing */

		  JFrame frame = new JFrame("Trade Cards");
		  frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		  frame.setResizable(false);

		  JPanel statusPnl = new JPanel();
		  statusPnl.setLayout(new GridLayout(0, 1));
		  JLabel statuslbl = new JLabel("Please select the any 3 cards you want to trade");
		  statusPnl.add(statuslbl);
		  frame.add(statusPnl, BorderLayout.NORTH);

		  JPanel CardPnl = new JPanel();
		  GridLayout experimentLayout = new GridLayout(0, 2);
		  CardPnl.setLayout(experimentLayout);
		  for (int i = 0; i <= risk.curPlayer.getCard().size() - 1; i++) {
			  JButton jbn = new JButton(risk.curPlayer.getCard().get(i).toString().split("-")[0]);
			  jbn.setName(risk.curPlayer.getCard().get(i).toString().split("-")[1] + "-"
					  + risk.curPlayer.getCard().get(i).toString().split("-")[2]);
			  CardPnl.add(jbn);
			  jbn.addActionListener(new ActionListener() {

				  @Override
				  public void actionPerformed(ActionEvent arg0) {

					  // if(!isAlreadyAdded(jbn))
					  // {

				  }

			  });
			  jbn.addMouseListener(new java.awt.event.MouseAdapter() {
				  public void mouseClicked(java.awt.event.MouseEvent evt) {
					  if (jbn.isEnabled()) {
						  lstTradedCards.add(new RiskCardModel(Integer.valueOf(jbn.getName().split("-")[0]),
								  jbn.getName().split("-")[1]));
						  countTradeCards++;
						  jbn.setEnabled(false);
						  statuslbl.setText(String.valueOf(countTradeCards));
					  }

					  else {
						  jbn.setEnabled(true);

						  if (isAlreadyAdded(jbn)) {
							  for (RiskCardModel card : lstTradedCards) {
								  if (card.value.equals(jbn.getName().split("-")[1])
										  && card.territory == Integer.valueOf(jbn.getName().split("-")[0])) {
									  lstTradedCards.remove(card);
									  countTradeCards--;
									  break;
								  }
							  }
							  statuslbl.setText(String.valueOf(countTradeCards));
						  }
					  }
				  }
			  });
		  }
		  frame.add(CardPnl, BorderLayout.CENTER);

		  JPanel btnPnl = new JPanel();
		  btnPnl.setLayout(new GridLayout(0, 2));
		  JButton applyButton = new JButton("OK");
		  btnPnl.add(new Label(""));
		  btnPnl.add(applyButton);
		  frame.add(btnPnl, BorderLayout.SOUTH);

		  frame.pack();
		  frame.setVisible(true);

		  applyButton.addActionListener((ActionEvent ave) -> {
			  if (countTradeCards < 3)
				  statuslbl.setText("Please select only/atleast three cards!");
			  else {
				  // Logic to add armies!
				  if (isTradedCardSetValid())
					  // risk.curPlayer.addArmies(risk.FetchTradedArmiesCount());
					  statuslbl.setText("Success");

				  else
					  statuslbl.setText("Select 3 different cards or two different and one wild card!");
			  }
		  });

	  }

	  /**
  	 * Checks if is already added.
  	 *
  	 * @param jbn
  	 * @return true, if is already added
  	 */
  	private boolean isAlreadyAdded(JButton jbn) {

		  for (RiskCardModel card : lstTradedCards) {
			  if (card.territory == Integer.valueOf(jbn.getName().split("-")[0])
					  && card.value.equals(jbn.getName().split("-")[1]))
				  return true;
		  }
		  return false;
	  }

	  /**
  	 * Checks if is traded card set valid.
  	 *
  	 * @return the boolean
  	 */
  	public Boolean isTradedCardSetValid() {
		  if (lstTradedCards.size() > 3)
			  return false;
		  else if (lstTradedCards.size() == 3) {
			  if (!isAWildCardSet()) {
				  // If all the cards are same
				  if (lstTradedCards.get(0).value.equals(lstTradedCards.get(1).value)
						  && lstTradedCards.get(1).value.equals(lstTradedCards.get(2).value)) {
					  return true;
				  }
				  // all 3 are different
				  else if (!lstTradedCards.get(0).value.equals(lstTradedCards.get(1).value)
						  && !lstTradedCards.get(0).value.equals(lstTradedCards.get(2).value)) {
					  if (!lstTradedCards.get(1).value.equals(lstTradedCards.get(2).value)) {
						  return true;
					  } else
						  return false;
				  }
			  } else
				  return isValidCountWildCard();
		  } else
			  return false;

		  return false;
	  }

	  /**
  	 * Checks if is A wild card set.
  	 *
  	 * @return the boolean
  	 */
  	public Boolean isAWildCardSet() {
		  for (RiskCardModel card : lstTradedCards) {
			  if (card.value.equals("WILD"))
				  return true;
		  }
		  return false;
	  }

	  /**
  	 * Checks if is valid count wild card.
  	 *
  	 * @return the boolean
  	 */
  	public Boolean isValidCountWildCard() {
		  int count = 0;
		  for (RiskCardModel card : lstTradedCards) {
			  if (card.value.equals("WILD")) {
				  count++;
			  }
		  }
		  return (count > 1 ? false : true);
	  }

	  /**
  	 * Creates the layout.
  	 *
  	 */
  	private void createLayout(JComponent... arg) {

		  JPanel pane = (JPanel) getContentPane();
		  GroupLayout gl = new GroupLayout(pane);
		  pane.setLayout(gl);

		  pane.setToolTipText("Content pane");

		  gl.setAutoCreateContainerGaps(true);

		  gl.setHorizontalGroup(gl.createSequentialGroup().addComponent(arg[0]).addGap(200));

		  gl.setVerticalGroup(gl.createSequentialGroup().addComponent(arg[0]).addGap(120));

		  pack();
	  }

	 
  	void saySomething(String desc, MouseEvent e) {
		  // jTextArea1.append(desc + " (" + e.getX() + "," + e.getY() + ")\n");
	  }

	  /* (non-Javadoc)
  	 * @see java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent)
  	 */
  	public void mousePressed(MouseEvent me) {
	  }

	  /* (non-Javadoc)
  	 * @see java.awt.event.MouseListener#mouseReleased(java.awt.event.MouseEvent)
  	 */
  	public void mouseReleased(MouseEvent me) {
	  }

	  /* (non-Javadoc)
  	 * @see java.awt.event.MouseListener#mouseEntered(java.awt.event.MouseEvent)
  	 */
  	public void mouseEntered(MouseEvent me) {
	  }

	  /* (non-Javadoc)
  	 * @see java.awt.event.MouseListener#mouseExited(java.awt.event.MouseEvent)
  	 */
  	public void mouseExited(MouseEvent me) {
	  }
}