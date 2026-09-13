// Converts Blockly blocks into the JSON format required by the assignment.

export function generateProgram(workspace) {
  const program = [];

  // Get all blocks that are not inside another block.
  // These are the top-level blocks of our program.
  const topBlocks = workspace.getTopBlocks(true);

  for (const block of topBlocks) {
    program.push(generateBlock(block));
  }

  return {
    program: program,
  };
}


// Converts one Blockly block into our JSON format.
function generateBlock(block) {
  switch (block.type) {

    case "move":
      return {
        type: "move",
        steps: block.getFieldValue("STEPS"),
      };


    case "turn":
      return {
        type: "turn",
        direction: block.getFieldValue("DIRECTION"),
      };


    case "say":
      return {
        type: "say",
        text: block.getFieldValue("TEXT"),
      };


    case "repeat": {
  const times = Number(block.getFieldValue("TIMES"));

  // Repeat count must not be negative.
  if (times < 0) {
    throw new Error("Repeat count cannot be negative");
  }

  const body = [];

  let child = block.getInputTargetBlock("BODY");

  while (child) {
    body.push(generateBlock(child));
    child = child.getNextBlock();
  }

  return {
    type: "repeat",
    times: times,
    body: body,
  };
}


    default:
      throw new Error(`Unknown block type: ${block.type}`);
  }
}