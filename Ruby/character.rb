module Character
	attr_accessor :slogan 
	attr_writer :name
	
	def hello
		return "Hi, my name is #{@name}"
	end
end