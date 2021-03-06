package mage.abilities.keyword;

import mage.abilities.SpellAbility;
import mage.abilities.costs.common.ExileFromGraveCost;
import mage.abilities.costs.mana.ManaCostsImpl;
import mage.cards.Card;
import mage.constants.SpellAbilityType;
import mage.constants.Zone;
import mage.filter.FilterCard;
import mage.filter.predicate.permanent.AnotherPredicate;
import mage.target.common.TargetCardInYourGraveyard;
import mage.util.CardUtil;

/**
 * @author TheElk801
 */
public class EscapeAbility extends SpellAbility {

    private static final FilterCard filter = new FilterCard("card to exile");

    static {
        filter.add(AnotherPredicate.instance);
    }

    private final String manaCost;
    private final int exileCount;

    public EscapeAbility(Card card, String manaCost, int exileCount) {
        super(card.getSpellAbility());
        this.newId();
        this.setCardName(card.getName() + " with Escape");
        this.zone = Zone.GRAVEYARD;
        this.spellAbilityType = SpellAbilityType.BASE_ALTERNATE;

        this.manaCost = manaCost;
        this.exileCount = exileCount;
        this.getManaCosts().clear();
        this.getManaCostsToPay().clear();
        this.addManaCost(new ManaCostsImpl(manaCost));
        this.addCost(new ExileFromGraveCost(new TargetCardInYourGraveyard(exileCount, filter)));
    }

    private EscapeAbility(final EscapeAbility ability) {
        super(ability);
        this.manaCost = ability.manaCost;
        this.exileCount = ability.exileCount;
    }

    @Override
    public EscapeAbility copy() {
        return new EscapeAbility(this);
    }

    @Override
    public String getRule(boolean all) {
        return getRule();
    }

    @Override
    public String getRule() {
        return "Escape&mdash;" + this.manaCost + ", Exile " + CardUtil.numberToText(this.exileCount) +
                " other cards from your graveyard. <i>(You may cast this card from your graveyard for its escape cost.)</i>";
    }
}
