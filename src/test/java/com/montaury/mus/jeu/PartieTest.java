package com.montaury.mus.jeu;

import com.montaury.mus.jeu.joueur.AffichageEvenementsDeJeu;
import com.montaury.mus.jeu.joueur.InterfaceJoueur;
import com.montaury.mus.jeu.joueur.Joueur;
import com.montaury.mus.jeu.joueur.Opposants;
import com.montaury.mus.jeu.tour.phases.dialogue.Hordago;
import com.montaury.mus.jeu.tour.phases.dialogue.Kanta;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class PartieTest {

  @BeforeEach
  void setUp() {
    interfaceJoueurEsku = mock(InterfaceJoueur.class);
    interfaceJoueurZaku = mock(InterfaceJoueur.class);
    interfaceJoueurNormal1 = mock(InterfaceJoueur.class);
    interfaceJoueurNormal2 = mock(InterfaceJoueur.class);
    Joueur joueurEsku = new Joueur("J1", interfaceJoueurEsku);
    Joueur joueurZaku = new Joueur("J2", interfaceJoueurZaku);
    Joueur joueurNormal1 = new Joueur("J3", interfaceJoueurNormal1);
    Joueur joueurNormal2 = new Joueur("J4", interfaceJoueurNormal2);

    Equipe equipeEsku = new Equipe("E1", joueurEsku, joueurNormal1);
    Equipe equipeZaku = new Equipe("E2", joueurNormal2, joueurZaku);

    opposants = new Opposants(equipeEsku, equipeZaku);
    partie = new Partie(mock(AffichageEvenementsDeJeu.class));
  }

  @Test
  void devrait_faire_gagner_le_premier_joueur_a_3_manches() {
    when(interfaceJoueurEsku.faireChoixParmi(any())).thenReturn(new Hordago());
    when(interfaceJoueurNormal2.faireChoixParmi(any())).thenReturn(new Kanta());
    when(interfaceJoueurNormal1.faireChoixParmi(any())).thenReturn(new Kanta());
    when(interfaceJoueurZaku.faireChoixParmi(any())).thenReturn(new Kanta());

    Partie.Resultat resultat = partie.jouerPartie(opposants);

    assertThat(resultat.vainqueur()).isNotNull();
    assertThat(resultat.score().resultatManches()).hasSizeGreaterThanOrEqualTo(3);
  }

  private InterfaceJoueur interfaceJoueurEsku;
  private InterfaceJoueur interfaceJoueurZaku;
  private InterfaceJoueur interfaceJoueurNormal1;
  private InterfaceJoueur interfaceJoueurNormal2;
  private Opposants opposants;
  private Partie partie;
}