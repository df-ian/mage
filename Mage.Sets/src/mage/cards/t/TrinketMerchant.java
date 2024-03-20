package mage.cards.t;

import mage.ApprovingObject;
import mage.MageInt;
import mage.abilities.Ability;
import mage.abilities.common.EntersBattlefieldTriggeredAbility;
import mage.abilities.common.SimpleActivatedAbility;
import mage.abilities.costs.common.TapSourceCost;
import mage.abilities.effects.OneShotEffect;
import mage.abilities.hint.Hint;
import mage.cards.Card;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.cards.repository.CardCriteria;
import mage.cards.repository.CardInfo;
import mage.cards.repository.CardRepository;
import mage.choices.Choice;
import mage.choices.ChoiceHintType;
import mage.choices.ChoiceImpl;
import mage.constants.*;
import mage.game.Game;
import mage.players.Player;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Ian
 */
public final class TrinketMerchant extends CardImpl {

    public TrinketMerchant(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.CREATURE}, "{1}{U}");

        this.subtype.add(SubType.HUMAN);
        this.subtype.add(SubType.ARTIFICER);
        this.power = new MageInt(1);
        this.toughness = new MageInt(3);

        this.addAbility(new EntersBattlefieldTriggeredAbility(
                new TrinketMerchantEffect()));
    }

    private TrinketMerchant(final TrinketMerchant card) {
        super(card);
    }

    @Override
    public TrinketMerchant copy() {
        return new TrinketMerchant(this);
    }
}

class TrinketMerchantEffect extends OneShotEffect {

    private static final List<String> names = Arrays.asList(
            "Mox Pearl", "Mox Sapphire", "Mox Jet", "Mox Ruby", "Mox Emerald", "Black Lotus"
    );
    private static final Map<String, Card> cardMap = new HashMap<>();

    private static final void initMap() {
        if (!cardMap.isEmpty()) {
            return;
        }
        cardMap.putAll(CardRepository
                .instance
                .findCards(new CardCriteria().setCodes("LEA"))
                .stream()
                .filter(cardInfo -> names.contains(cardInfo.getName()))
                .collect(Collectors.toMap(CardInfo::getName, CardInfo::createCard)));
    }

    TrinketMerchantEffect() {
        super(Outcome.Benefit);
        staticText = "choose a card name that hasn't been chosen from among " +
                "the five mox and Black Lotus. " +
                "Create a copy of the card with the chosen name. You may cast the copy";
    }

    private TrinketMerchantEffect(final TrinketMerchantEffect effect) {
        super(effect);
    }

    @Override
    public TrinketMerchantEffect copy() {
        return new TrinketMerchantEffect(this);
    }

    @Override
    public boolean apply(Game game, Ability source) {
        initMap();
        Player player = game.getPlayer(source.getControllerId());
        if (player == null) {
            return false;
        }
        Set<String> alreadyChosen = getAlreadyChosen(game, source);
        Set<String> choices = new LinkedHashSet<>(names);
        choices.removeAll(alreadyChosen);
        String chosen;
        switch (choices.size()) {
            case 0:
                return false;
            case 1:
                chosen = choices.stream().findAny().orElse(null);
                break;
            default:
                Choice choice = new ChoiceImpl(true, ChoiceHintType.CARD);
                choice.setChoices(choices);
                player.choose(outcome, choice, game);
                chosen = choice.getChoice();
        }
        alreadyChosen.add(chosen);
        Card card = cardMap.get(chosen);
        if (card == null || !player.chooseUse(outcome, "Cast " + card.getName() + '?', source, game)) {
            return false;
        }
        Card copiedCard = game.copyCard(card, source, source.getControllerId());
        copiedCard.setZone(Zone.OUTSIDE, game);
        game.getState().setValue("PlayFromNotOwnHandZone" + copiedCard.getId(), Boolean.TRUE);
        player.cast(
                player.chooseAbilityForCast(copiedCard, game, false),
                game, false, new ApprovingObject(source, game)
        );
        game.getState().setValue("PlayFromNotOwnHandZone" + copiedCard.getId(), null);
        return true;
    }

    private static final Set<String> getAlreadyChosen(Game game, Ability source) {
        String key = getKey(source);
        Object value = game.getState().getValue(key);
        if (value instanceof Set) {
            return (Set<String>) value;
        }
        Set<String> alreadyChosen = new HashSet<>();
        game.getState().setValue(key, alreadyChosen);
        return alreadyChosen;
    }

    static final String getKey(Ability source) {
        return source.getSourceId() + "_"
                + source.getSourceObjectZoneChangeCounter() + "_"
                + source.getOriginalId() + "_garth";
    }
}
