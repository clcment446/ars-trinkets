# Ars Trinkets 3.1.1

- Fixed mobs spawning near a player who never levelled skipping the level/core cap (they spawned at full natural strength). Players now always count, as level 0.
- New `mob_scaling.player_cap_radius` (default 128): only players within this distance affect a spawning mob's cap and roaming difficulty.
- New `mob_scaling.player_cap_mode` (`NEAREST` default, `LOWEST`, `HIGHEST`): choose which player in range caps the mob. `LOWEST` protects low-level players sharing a server with a high-level one.
- Removed the unused `world_difficulty_per_level` config option (it never affected anything).

# Ars Trinkets 3.1.0

- Essence drop balance: gated by the `essence_drops_enabled` config AND the new `arsTrinketsEssenceDrops` gamerule; never drops an essence the killer cannot use (`essence_drop_only_usable`); capped per kill (`essence_drop_max_count`); default chance lowered to 35% and extras to 0.1 per core.
- Killing mobs now awards essence: hostile mobs killed by a player (or their spells) drop the essence matching the mob's level, plus extra per core. Configurable (`essence_drops_enabled`, `essence_drops_hostile_only`, `essence_drop_chance`, `essence_drop_extra_per_core`).
- Devour Soul now boosts essence yield: a mob hit by it and killed by the caster within a minute drops a guaranteed, multiplied (`devour_soul_essence_yield_multiplier`, scales with Amplify) amount. It also no longer silently does nothing when the caster has 0 souls.
- Essences are no longer a pain to use: always edible, eaten fast, never wasted when too weak for you, sneak to eat the whole stack, action-bar feedback. Level-ups now carry overflow souls and no longer index out of bounds.
- Every trinket (runes, crown, rings, lotuses) is now configurable through `config/ars_trinkets/curios_config.json`, which is auto-generated on server start (new trinkets are appended, edits are kept) and hot-reloaded when edited. Supports `enabled`, `mode` (MERGE/OVERRIDE), single-object modifiers, operation aliases, deterministic `auto` ids and per-entry error tolerance.
- Essence cores are craftable again (Enchanting Apparatus, ported from 1.20.x).
- Fixed the Air Sword glyph sharing its recipe with Sonic Boom (now netherite sword + 3 air essence).
- Fixed duplicate `soul_quantity` config key (essence one is now `essence_soul_quantity`) and swapped ring/lotus amplification lists.

# Ars Trinkets 3.0.0

A major progression, combat, and spell-system update.

## New Content

- Added the **Devour Soul** glyph.
  - Consumes a configurable percentage of the caster's souls.
  - Damage scales with the number of souls consumed.
  - Includes a new recipe, model, particles, and configuration options.
- Added scalable mob progression.
  - Mobs now receive levels and cores based on their base stats.
  - Mob health, attack damage, and armor can scale independently.
  - Mob difficulty can be capped relative to nearby players.
- Added mob soul inspection.
  - Inspect Soul can reveal mob progression information.
  - Mob name tags can display their level and core rank.
- Added level-based bonus glyph slots.
  - Bonus slots can be configured per player level.
  - Supports stacking with Ars Nouveau's Infinite Spells system.
  - Server-side spell size enforcement prevents oversized spells.
- Expanded Omnipotence Crown functionality.
  - Grants flight while equipped.
  - Adds a visible extra-life HUD.
  - Crown lives now synchronize correctly between server and client.
  - Added configurable HUD positioning.

## Tribulations and Combat

- Added extensive configuration for mob scaling and combat normalization.
- Improved roaming mob difficulty scaling.
- Roaming health, attack, and resistance scaling can now be configured separately.
- Improved tribulation boss enhancements and resistance calculations.
- Player and mob damage scaling now account for levels and cores more consistently.
- Improved cleanup and state handling for player abilities, crown lives, and level bonuses.

## Technical Improvements

- Migrated the project from NeoGradle to ModDevGradle.
- Updated NeoForge and dependency versions for Minecraft 1.21.1.
- Improved mod metadata generation and packaging.
- Added networking support for client-side progression and HUD information.
- Improved `/ars-trinkets` administration commands and error handling.
