module BookKeeping
  VERSION = 3
end

class Game
  attr_reader :rolls

  def initialize
    @rolls = []
  end

  def roll(pins)
    fail BowlingError unless (0..10).include?(pins)
    @rolls.push(pins)
  end  

  def score
    fail BowlingError if invalid_score?
    calculate_score
  end

  def invalid_score?
    rolls.empty? ||
    rolls == [0,0] ||
    rolls.size > 20 && rolls.all?{|el| el == 0} || 
    rolls[-1] == 10 && rolls[-2] == 0 ||
    rolls[-1] == 10 && rolls[-2] == 10 && rolls[-3] == 0 ||
    rolls[-1] + rolls[-2] == 10 && rolls.size == 20
  end

  def calculate_score
    score_index = {score: 0, index: 0}
    while score_index[:index] < (rolls.size) do
      strike_or_spare_calculation(score_index)
    end
    score_index[:score]
  end

  def strike_or_spare_calculation(score_index)
    if strike?(score_index[:index])
      score_index[:score] += score_for_strike(score_index[:index])
      score_index[:index] = index_for_strike(score_index[:index])
    else
      score_index[:score] += score_for_spare(score_index[:index])
      score_index[:index] = index_for_spare(score_index[:index])
    end
    score_index
  end

  def strike?(index)
    rolls[index] == 10
  end

  def score_for_strike(index)
    fail BowlingError if invalid_strike_score?(index)
    rolls[index] + rolls[index+1] + rolls[index+2]
  end

  def invalid_strike_score?(index)
    rolls[index+1] < 10 && (rolls[index+1] + rolls[index+2]) > 10
  end

  def index_for_strike(index)
    index += 2 if rolls[index+3].nil?
    index += 1
    index
  end

  def score_for_spare(index)
    frame_points = rolls[index] + rolls[index+1] if index+1 < rolls.size
    fail BowlingError if frame_points > 10
    frame_points += rolls[index+2] if frame_points == 10
    frame_points
  end

  def index_for_spare(index)
    index += 1 if rolls[index+3].nil?
    index += 2
    index
  end

  class BowlingError < RuntimeError
  end
end