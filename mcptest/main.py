import asyncio
import os
import json
from mcp import ClientSession, StdioServerParameters
from mcp.client.stdio import stdio_client

async def main():
    db_path = os.getenv("SQLITE_DB_PATH")
    if not db_path:
        raise ValueError("Please set the SQLITE_DB_PATH environment variable before running the script.")
    absolute_db_path = os.path.abspath(db_path)
    server_script_path = './sqlite_explorer.py'
    server_params = StdioServerParameters(
        command="python",
        args=[server_script_path],
        env={"SQLITE_DB_PATH": absolute_db_path}
    )


    async with stdio_client(server_params) as streams:
        async with ClientSession(streams[0], streams[1]) as session:
            await session.initialize()
            print("Successfully connected to the SQLite Explorer server.")
            list_tools_response = await session.list_tools()
            tool_names = [tool.name for tool in list_tools_response.tools]
            print(f"Available tools: {tool_names}")

            print("\n--- Calling list_tables ---")
            tables_result = await session.call_tool("list_tables", {})
            print("Tables found:", tables_result.content[0].text)

            table_to_describe = tables_result.content[0].text
            for table in json.loads(table_to_describe):
                print(f"\n--- Calling describe_table for '{table}' ---")
                schema_result = await session.call_tool(
                    "describe_table",
                    {"table_name": table}
                )
                print(f"Schema for '{table}':", schema_result.content)
                query = f"SELECT * FROM {table}"
                print(f"\n--- Calling read_query with: '{query}' ---")
                query_result = await session.call_tool(
                    "read_query",
                    {"query": query}
                )
                print("Query result:", query_result.content)


if __name__ == "__main__":
    asyncio.run(main())
