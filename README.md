# UNO - Card Game
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=STAR-Uno_STAR-group1-uno&metric=coverage)](https://sonarcloud.io/summary/new_code?id=STAR-Uno_STAR-group1-uno)
[![Maintainability Rating](https://sonarcloud.io/api/project_badges/measure?project=STAR-Uno_STAR-group1-uno&metric=sqale_rating)](https://sonarcloud.io/summary/new_code?id=STAR-Uno_STAR-group1-uno)
[![Code Smells](https://sonarcloud.io/api/project_badges/measure?project=STAR-Uno_STAR-group1-uno&metric=code_smells)](https://sonarcloud.io/summary/new_code?id=STAR-Uno_STAR-group1-uno)
[![Technical Debt](https://sonarcloud.io/api/project_badges/measure?project=STAR-Uno_STAR-group1-uno&metric=sqale_index)](https://sonarcloud.io/summary/new_code?id=STAR-Uno_STAR-group1-uno)

> v2.0: Rebuilt the core game logic in domain-driven-design.

![uno-ui](./doc/new-uno-v2.gif)
*Note: GUI is built merely to try out the core domain logic. It is not suitable for serious game. :)*

## Instructions

**Run Application**

Run the `main` method in the UnoApp class or use the following command in the terminal:

```
./gradlew run
```

**Run Unit Tests**

```
./gradlew test
```

## Event Storming

![event-storming](./doc/event-storming.jpg).

## Core Domain

The core game logic is built according to [official uno rules](https://en.wikipedia.org/wiki/Uno_(card_game)#Official_rules).

### Uno Card

Cards are value objects, i.e. immutable. The following card types are available in Uno: 
* Number Card
* Skip Action Card
* Reverse Action Card
* Draw Two Action Card
* Wild Color Card
* Wild Draw Four Card

Initially wild cards don't have a color. When drawn, a new value object is created with the chosen color. 

![card-uml](./doc/cards.png)

### Player

`Player` is an entity which contains a list of hand cards to play.

`PlayerRoundIterator` manages the players and switches turn as if the players are in a round table.

![player-uml](./doc/player.png)

### Game

`Game` is the aggregate which maintains the state of **players**, **draw pile** and **discard pile** as the cards are played. 

![game-uml](./doc/game.png)

### Domain Events

`Game` aggregate produces domain events using `DomainEventPublisher`.

Subscribers can register for these events and handle them accordingly. 

![events-uml](./doc/events.png)
