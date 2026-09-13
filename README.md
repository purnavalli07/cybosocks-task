# Cybosocks Screening Task

A block-based turtle programming editor built with React and Blockly, with a Java runtime that executes the exported program JSON.

## Project Structure

- `src/` - React + Blockly editor
- `runner/` - Java runtime

## Part A - React + Blockly

### Requirements

- Node.js
- npm

### Run

Install dependencies:

```bash
npm install

Start the development server:

npm run dev

The editor provides four custom Blockly blocks:

Move
Turn
Say
Repeat

Repeat blocks support nested repeat blocks to any depth.

The JSON output is generated live from the Blockly workspace and includes a Copy JSON button.

## Part B - Java Runtime
Requirements
Java 23 or compatible Java version
Maven
Build

Open a terminal inside the runner/ directory:

cd runner
mvn clean package
Run

Run the generated JAR with a program JSON file:

java -jar target/runner.jar program.json

The Java runtime:

Starts the turtle at (0, 0) facing North
Supports move, turn, say, and repeat blocks
Supports nested repeat blocks
Prints say text during execution
Prints the final turtle position and direction
Handles invalid input with clear one-line error messages
## Part C - Notes
AI tools used

I used ChatGPT as a development assistant during this task.

I used it for:

Understanding and breaking down the requirements
Planning the React and Blockly implementation
Debugging React and Java code
Reviewing the custom JSON generator
Thinking through nested repeat handling
Testing edge cases and improving error handling

I tested and verified the final implementation locally rather than relying only on AI-generated code.

## One thing AI got wrong

While testing the nested repeat example, the AI initially calculated the turtle's final position incorrectly.

I noticed the mistake by manually tracing the turtle's movements and comparing the calculation with the actual output from my Java program. I then corrected the calculation and verified the result by running the program again.

## One decision I am unsure about

I kept the Blockly editor UI relatively simple and focused most of my time on getting the React-to-JSON and JSON-to-Java integration working correctly.

I think this was the right trade-off for the available time, but with another 5 hours I would improve the child-friendly UI, add clearer validation feedback, and add more automated tests for deeply nested repeat blocks and invalid JSON inputs.

### Part D - Demo Video
https://drive.google.com/file/d/14DrxC-aNWTWXf52wydE5r2-Xa8H9eiwH/view?usp=sharing