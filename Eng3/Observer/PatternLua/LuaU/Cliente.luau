local Cliente = {}
Cliente.__index = Cliente
export type Cliente = {
    name: string,
    itens: {{name: string}},
    onNewProductEvent: () -> (({name: string}) -> ())
}
function Cliente.new(name)
	local self = setmetatable({}, Cliente)
	self.name = name
	self.itens = {}
	return self
end

function Cliente:onNewProductEvent()
	return function(product)
		print("Cliente " .. self.name .. ", " .. product.name .. " foi adicionado ao estoque.")
	end
end

return Cliente
