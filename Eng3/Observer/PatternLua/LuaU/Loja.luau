local Publisher = require("./Publisher")

export type Loja = {
    itens: {{name: string}},
    publisher: Publisher.Publisher
}

local Loja = {}
Loja.__index = Loja

function Loja.new()
	local self = setmetatable({}, Loja)
	self.itens = {}
	self.publisher = Publisher.new()
	return self
end

function Loja:addProduct(product)
	table.insert(self.itens, product)
	self.publisher:publish(product)
end
return Loja
