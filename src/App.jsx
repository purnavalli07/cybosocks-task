import { useEffect, useRef, useState } from "react";
import * as Blockly from "blockly";
import { registerCustomBlocks } from "./blocks";
import { generateProgram } from "./generator";
import "./App.css";

function App() {
  const blocklyDiv = useRef(null);
  const workspace = useRef(null);

  const [program, setProgram] = useState({
    program: [],
  });

  useEffect(() => {
    // Register our custom blocks.
    registerCustomBlocks();

    // Create Blockly workspace.
    workspace.current = Blockly.inject(blocklyDiv.current, {
      toolbox: {
        kind: "flyoutToolbox",
        contents: [
          {
            kind: "block",
            type: "move",
          },
          {
            kind: "block",
            type: "turn",
          },
          {
            kind: "block",
            type: "say",
          },
          {
            kind: "block",
            type: "repeat",
          },
        ],
      },

      trashcan: true,
      scrollbars: true,
    });


    // Update JSON whenever the workspace changes.
    const handleWorkspaceChange = () => {
      try {
        const generatedProgram = generateProgram(
          workspace.current
        );

        setProgram(generatedProgram);
      } catch (error) {
        console.error(error);
      }
    };


    workspace.current.addChangeListener(
      handleWorkspaceChange
    );


    // Generate initial JSON.
    handleWorkspaceChange();


    // Cleanup.
    return () => {
      if (workspace.current) {
        workspace.current.dispose();
      }
    };
  }, []);


  return (
    <div className="app">

      <h1>Cybosocks Code Builder</h1>

      <div className="editor-container">

        {/* Blockly Workspace */}
        <div
          ref={blocklyDiv}
          className="blockly-workspace"
        ></div>


        {/* JSON Panel */}
        <div className="json-panel">

  <h2>JSON Output</h2>

  <button
    onClick={() => {
      navigator.clipboard.writeText(
        JSON.stringify(program, null, 2)
      );
    }}
  >
    Copy JSON
  </button>

  <pre>
    {JSON.stringify(program, null, 2)}
  </pre>

</div>

      </div>

    </div>
  );
}

export default App;