local Cliente = require("./Cliente")
local Publisher = {}

Publisher.__index = Publisher
export type Publisher = {
    listeners: {{listener: Cliente.Cliente, callback: (any) -> ()}}
}
function Publisher.new()
	local self = setmetatable({}, Publisher)
	self.listeners = {}
	return self
end

function Publisher:subscribe(listener, callback)
	table.insert(self.listeners, { listener, callback })
end

function Publisher:unsubscribe(listener)
	for i, v in ipairs(self.listeners) do
		if v[1] == listener then
			table.remove(self.listeners, i)
			break
		end
	end
end

function Publisher:publish(...)
	for _, listener in ipairs(self.listeners) do
		listener[2](...)
	end
end

return Publisher
