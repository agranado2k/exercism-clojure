  module BookKeeping
  VERSION = 2
end

class BeerSong
  def lyrics
    verses(99,0)
  end

  def verses(first_verse, last_verse)
    first_verse.downto(last_verse).inject([]){|result, verse| result << verse(verse)}.join("\n")
  end

  def verse(number)
    smart_number = SmartNumber.for(number)

    "#{smart_number.quantity.capitalize} #{smart_number.container} of beer on the wall, " +
    "#{smart_number.quantity} #{smart_number.container} of beer.\n"  +
    "#{smart_number.action}, " +
    "#{smart_number.successor.quantity} #{smart_number.successor.container} of beer on the wall.\n"
  end
end

class SmartNumber
  attr_reader :number

  def self.for(number)
    return number if number.is_a?(SmartNumber)
    
    case number
    when 0
      SmartNumber0
    when 1
      SmartNumber1
    else
      SmartNumber
    end.new(number)
  end

  def initialize(number)
    @number = number
  end

  def container
    "bottles"
  end

  def quantity
    number.to_s
  end

  def pronoun
    "one"
  end

  def successor
    SmartNumber.for(number - 1)
  end

  def action
    "Take #{pronoun} down and pass it around"
  end
end

class SmartNumber0 < SmartNumber
  def quantity
    "no more"
  end

  def successor
    SmartNumber.for(99)
  end

  def action
    "Go to the store and buy some more"
  end
end

class SmartNumber1 < SmartNumber
  def container
    "bottle"
  end

  def pronoun
    "it"
  end
end 

