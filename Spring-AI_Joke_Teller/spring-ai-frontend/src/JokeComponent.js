import React, { useState } from 'react';
import axios from 'axios';
import './JokeComponent.css';

const JokeComponent = () => {
  const [topic, setTopic] = useState('');
  const [joke, setJoke] = useState('');

  const getJoke = async () => {
    try {
      const response = await axios.get(`http://localhost:8090/ai/jokes?topic=${topic}`);
      setJoke(response.data);
    } catch (error) {
      console.error('Error fetching joke:', error);
    }
  };

  return (
    <div>
      <label>
        Enter a topic:
        <input type="text" value={topic} onChange={(e) => setTopic(e.target.value)} />
      </label>
      <button className='btn' onClick={getJoke}>Get Joke</button>
      {joke && <p>{joke.split(":")[1]?.trim()}</p>}
    </div>
  );
};

export default JokeComponent;