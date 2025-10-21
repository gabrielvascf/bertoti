from mcp2py import load
import dspy

lm = dspy.LM('ollama_chat/qwen3:1.7b', api_base='http://localhost:11434', api_key='')
dspy.configure(lm=lm)

browser =load("pnpm chrome-devtools-mcp --headless")

browser_agent = dspy.ReAct("command -> results", tools=browser.tools)

res = browser_agent(command="")

print(res.results)
