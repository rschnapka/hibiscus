/**********************************************************************
 * $Source: /cvsroot/hibiscus/hibiscus/src/de/willuhn/jameica/hbci/gui/views/DauerauftragNew.java,v $
 * $Revision: 1.15 $
 * $Date: 2011/04/11 16:48:33 $
 * $Author: willuhn $
 * $Locker:  $
 * $State: Exp $
 *
 * Copyright (c) by willuhn.webdesign
 * All rights reserved
 *
 **********************************************************************/
package de.willuhn.jameica.hbci.gui.views;

import de.willuhn.jameica.gui.AbstractView;
import de.willuhn.jameica.gui.GUI;
import de.willuhn.jameica.gui.internal.parts.PanelButtonPrint;
import de.willuhn.jameica.gui.util.ColumnLayout;
import de.willuhn.jameica.gui.util.Container;
import de.willuhn.jameica.gui.util.SimpleContainer;
import de.willuhn.jameica.hbci.HBCI;
import de.willuhn.jameica.hbci.gui.controller.DauerauftragControl;
import de.willuhn.jameica.hbci.io.print.PrintSupportDauerauftrag;
import de.willuhn.jameica.hbci.rmi.Dauerauftrag;
import de.willuhn.jameica.system.Application;
import de.willuhn.util.I18N;

/**
 * Zeigt die Details eines Dauerauftrages an.
 */
public class DauerauftragNew extends AbstractView
{
  private final static I18N i18n = Application.getPluginLoader().getPlugin(HBCI.class).getResources().getI18N();

  /**
   * @see de.willuhn.jameica.gui.AbstractView#bind()
   */
  public void bind() throws Exception
  {
		final DauerauftragControl control = new DauerauftragControl(this);

		GUI.getView().setTitle(i18n.tr("Dauerauftrag bearbeiten"));
    GUI.getView().addPanelButton(new PanelButtonPrint(new PrintSupportDauerauftrag((Dauerauftrag) control.getTransfer())));
		
    Container konten = new SimpleContainer(getParent());
    konten.addHeadline(i18n.tr("Konten"));
		konten.addLabelPair(i18n.tr("Pers�nliches Konto"),			  control.getKontoAuswahl());

    ColumnLayout columns = new ColumnLayout(getParent(),2);

    {
      // Links
      Container left = new SimpleContainer(columns.getComposite());
      left.addHeadline(i18n.tr("Empf�nger"));
      left.addLabelPair(i18n.tr("Name"),                      control.getEmpfaengerName());
      left.addLabelPair(i18n.tr("Kontonummer"),               control.getEmpfaengerKonto());
      left.addLabelPair(i18n.tr("BLZ"),                       control.getEmpfaengerBlz());
      left.addCheckbox(control.getStoreEmpfaenger(),i18n.tr("In Adressbuch �bernehmen"));
    }
    {
      // Rechts
      Container right = new SimpleContainer(columns.getComposite());
      right.addHeadline(i18n.tr("Turnus"));
      right.addLabelPair(i18n.tr("Zahlungsturnus"),           control.getTurnus());
      right.addLabelPair(i18n.tr("Erste Zahlung"),            control.getErsteZahlung());
      right.addLabelPair(i18n.tr("Letzte Zahlung"),           control.getLetzteZahlung());
    }
		
    Container details = new SimpleContainer(getParent());
    details.addHeadline(i18n.tr("Details"));
    details.addLabelPair(i18n.tr("Verwendungszweck"),          control.getZweck());
    details.addLabelPair(i18n.tr("weiterer Verwendungszweck"), control.getZweck2());
    details.addLabelPair(i18n.tr("Textschl�ssel"),            control.getTextSchluessel());
	  details.addLabelPair(i18n.tr("Betrag"),                    control.getBetrag());
	  details.addSeparator();
	  details.addLabelPair(i18n.tr("Auftragsnummer"),            control.getOrderID());
  }
}
