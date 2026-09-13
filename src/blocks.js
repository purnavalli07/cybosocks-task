import * as Blockly from "blockly";

export function registerCustomBlocks() {
  // 1. MOVE BLOCK
  Blockly.Blocks["move"] = {
    init: function () {
      this.appendDummyInput()
        .appendField("move")
        .appendField(
          new Blockly.FieldNumber(1, 0),
          "STEPS"
        )
        .appendField("steps");

      this.setPreviousStatement(true, null);
      this.setNextStatement(true, null);

      this.setColour(120);

      this.setTooltip("Move forward by the given number of steps.");
    },
  };

  // 2. TURN BLOCK
  Blockly.Blocks["turn"] = {
    init: function () {
      this.appendDummyInput()
        .appendField("turn")
        .appendField(
          new Blockly.FieldDropdown([
            ["left", "left"],
            ["right", "right"],
          ]),
          "DIRECTION"
        );

      this.setPreviousStatement(true, null);
      this.setNextStatement(true, null);

      this.setColour(210);

      this.setTooltip("Turn 90 degrees left or right.");
    },
  };

  // 3. SAY BLOCK
  Blockly.Blocks["say"] = {
    init: function () {
      this.appendDummyInput()
        .appendField("say")
        .appendField(
          new Blockly.FieldTextInput("Hello!"),
          "TEXT"
        );

      this.setPreviousStatement(true, null);
      this.setNextStatement(true, null);

      this.setColour(45);

      this.setTooltip("Say a message.");
    },
  };

  // 4. REPEAT BLOCK
  Blockly.Blocks["repeat"] = {
    init: function () {
      this.appendDummyInput()
        .appendField("repeat")
        .appendField(
          new Blockly.FieldNumber(2, 0),
          "TIMES"
        )
        .appendField("times");

      this.appendStatementInput("BODY")
        .appendField("do");

      this.setPreviousStatement(true, null);
      this.setNextStatement(true, null);

      this.setColour(270);

      this.setTooltip(
        "Repeat the blocks inside this block."
      );
    },
  };
}