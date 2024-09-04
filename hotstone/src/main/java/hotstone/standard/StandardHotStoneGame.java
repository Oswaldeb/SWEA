/*
 * Copyright (C) 2022-2024. Henrik Bærbak Christensen, Aarhus University.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 *
 * You may obtain a copy of the License at
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package hotstone.standard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import hotstone.framework.*;

/** This is the 'temporary test stub' in TDD
 * terms: the initial empty but compilable implementation
 * of the game interface.
 *
 * It already includes a bit of FAKE-IT code for the first
 * test case about hand management and player in turn.
 *
 * Start solving the AlphaStone exercise by
 * following the TDD rhythm: pick a one-step-test
 * from your test list, quickly add a test,
 * run it to see it fail, and then modify this
 * implementing class (and supporting classes)
 * to make your test case run. Refactor and repeat.
 *
 * While this is the implementation of Game for
 * the AlphaStone game, you will constantly
 * refactor it over the course of the exercises
 * to become the 'core implementation' which will
 * enable a lot of game variants. This is also
 * why it is not called 'AlphaGame'.
 */


public class StandardHotStoneGame implements Game {
  private int Turn = 0;
  private int DeckSize = 7;

  Card Tres = new StandardCard(GameConstants.TRES_CARD, 3, 3, 3);
  Card Dos = new StandardCard(GameConstants.DOS_CARD, 2, 2, 2);
  Card Uno = new StandardCard(GameConstants.UNO_CARD, 1, 1, 1);
  Hero Findus_Baby = new StandHero(GameConstants.BABY_HERO_TYPE, 3, GameConstants.HERO_MAX_HEALTH, Player.FINDUS);
  Hero Peddersen_Baby = new StandHero(GameConstants.BABY_HERO_TYPE, 3, GameConstants.HERO_MAX_HEALTH, Player.PEDDERSEN);

  // First hand
  List<Card> hand = Arrays.asList(Tres, Dos, Uno);


  // Findus Field
  List<Card> Findus_Field = new ArrayList<Card>();
  // Peddersen Field
  List<Card> Peddersen_Field = new ArrayList<Card>(); 

  
  @Override
  public Player getPlayerInTurn() {
    if (Turn % 2 == 0){
      return Player.FINDUS;
    } else {
      return Player.PEDDERSEN;
    }
  } 

  @Override
  public Hero getHero(Player who) {
    if (who == Player.FINDUS){
      return Findus_Baby;
    } else {
      return Peddersen_Baby;
    } 
  }

  @Override
  public Player getWinner() {
    return null;
  }

  @Override
  public int getTurnNumber() {
    return Turn;
  }

  @Override
  public int getDeckSize(Player who) {
    return 0;
  }

  @Override
  public Card getCardInHand(Player who, int indexInHand) {
    //Given player is Findus
    if(who == Player.FINDUS){
      //Get Card from IndexInHand
      return hand.get(indexInHand);
    } else {
      return null;
    }
}

  @Override
  public Iterable<? extends Card> getHand(Player who) {
    return hand;
  }

  @Override
  public int getHandSize(Player who) {
    return 3;
  } // FAKE-IT

  @Override
  public Card getCardInField(Player who, int indexInField) {
    //depending on who is the player, get the field
    List<Card> field = (who == Player.FINDUS) ? Findus_Field : Peddersen_Field;
    return field.get(indexInField);
  }

  @Override
  public Iterable<? extends Card> getField(Player who) {
    List<Card> field = (who == Player.FINDUS) ? Findus_Field : Peddersen_Field;
    return field;
  }

  @Override
  public int getFieldSize(Player who) {
    //Given player is Findus
    if (who == Player.FINDUS){
      return Findus_Field.size();
      // Given player is Peddersen
    } else if  (who == Player.PEDDERSEN);
    return Peddersen_Field.size();
    
  }

  @Override
  public void endTurn() { 
    Turn ++;
  }

  @Override
  public Status playCard(Player who, Card card, int atIndex) {
    StandHero hero = (StandHero) getHero(who);
    hero.ReduceMana(card.getManaCost());
    Findus_Field.add(card);

    return null;
  }

  @Override
  public Status attackCard(Player playerAttacking, Card attackingCard, Card defendingCard) {
    return null;
  }

  @Override
  public Status attackHero(Player playerAttacking, Card attackingCard) {
    return null;
  }

  @Override
  public Status usePower(Player who) {
    return null;
  }
}
