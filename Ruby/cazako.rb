#require "C:/Users/khaos/Desktop/Lynda Learning/Ruby/Ruby Essential Training/cazako.rb"
require './character.rb'
class Cazako

	include Character
	include Enumerable
	
	@@copyright = "Cazako Creative © 2017"
	@@author = "Erik the Red"
	@@characters = []
	
	
	def self.copyright
		@@copyright
	end
	
	def self.author
		@@author
	end
	
	def self.characters
		@@characters
	end
	
	def initialize(name = "A mysterious character", slogan = "Cazako Creative")
		@slogan = slogan
		@name = name
		@weapon = "a random stick off the ground"
		@@characters << self
		"A new character has appeared!!"
	end
	
	
	def setWeapon(string)
		@weapon = string
	end
	
	def weapon=(string)
		@weapon = string
	end
		
	def Attack
		"#{@name} attacks with a #{@weapon}"
	end
	
	def each
		[@name, @slogan, @weapon].each {|item| yield item}
	end
	
end

module Heroes
	class KhaosKlub < Cazako
	
		include Character
		
		attr_accessor :team
		
		def initialize(name = "A mysterious character", slogan = "Cazako Creative")
			@slogan = slogan
			@name = name
			@weapon = "fist"
			@@characters << self
			"A new character has appeared!!"
		end
		
	end
end