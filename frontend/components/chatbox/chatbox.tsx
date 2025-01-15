import React, { useState, useRef, useEffect } from "react";
import { Mic, SendHorizonal, Loader } from "lucide-react";
import { Button } from "../ui/button";
import { Textarea } from "../ui/textarea";

export default function ChatBox() {
  const [messages, setMessages] = useState([]); // State to store chat messages
  const [inputValue, setInputValue] = useState(""); // State for the textarea value
  const [loading, setLoading] = useState(false); // State for loading spinner
  const [currentQuestionIndex, setCurrentQuestionIndex] = useState(0); // Tracks current question
  const chatEndRef = useRef(null); // Ref to scroll to the bottom of the chat
  const textareaRef = useRef(null); // Ref to reset textarea height

  // List of predefined questions and responses
  const questions = [
    {
      question: "How often do you feel restless or on edge?",
      response: "Based on your response, your restlessness levels are moderate. Remember that occasional restlessness is normal. \nDo you have trouble controlling your worries?"
    },
    {
      question: "Do you have trouble controlling your worries?",
      response: "It's common to struggle with controlling worries. Consider practicing mindfulness techniques.\nHow is your sleep quality lately?"
    },
    {
      question: "How is your sleep quality lately?",
      response: "Sleep disruption can significantly impact anxiety levels. Maintaining good sleep hygiene is important.\nDo you experience physical symptoms like rapid heartbeat or sweating?"
    },
    {
      question: "Do you experience physical symptoms like rapid heartbeat or sweating?",
      response: "Physical symptoms are common manifestations of anxiety. Try deep breathing exercises when they occur."
    },
  ];

  const handleSendMessage = () => {
    if (inputValue.trim()) {
      const userMessage = inputValue;
      setMessages((prevMessages) => [
        ...prevMessages,
        { sender: "user", text: userMessage }
      ]); // Add user's message
      setInputValue(""); // Clear the textarea
      resetTextareaHeight(); // Reset the height of the textarea
      setLoading(true); // Show the loading spinner

      // Simulate bot response
      setTimeout(() => {
        const currentQuestion = questions[currentQuestionIndex];
        if (currentQuestion) {
          setMessages((prevMessages) => [
            ...prevMessages,
            { sender: "bot", text: currentQuestion.response }
          ]); // Add bot's response
          setCurrentQuestionIndex((prevIndex) => prevIndex + 1); // Move to next question
        } else {
          setMessages((prevMessages) => [
            ...prevMessages,
            { sender: "bot", text: "Thank you for your responses. Let me know if you need further assistance!" }
          ]);
        }
        setLoading(false); // Hide the loading spinner
      }, 1000); // Delay for realism
    }
  };

  const resetTextareaHeight = () => {
    if (textareaRef.current) {
      textareaRef.current.style.height = "36px"; // Reset to the default height
    }
  };

  // Scroll to the bottom of the chat when a new message is added
  useEffect(() => {
    if (chatEndRef.current) {
      chatEndRef.current.scrollIntoView({ behavior: "smooth" });
    }
  }, [messages]);

  return (
    <div className="flex flex-1">
      <div className="px-2 md:py-10 md:px-60 rounded-tl-2xl border border-neutral-200 dark:border-neutral-700 bg-white dark:bg-neutral-900 flex flex-col flex-1 w-full h-full">
        {/* Chat Area */}
        <main className="flex-1 overflow-y-auto py-6 flex flex-col justify-start h-full max-h-full">
          <div className="flex flex-col gap-3 w-full h-full">
            {/* Introductory Text */}
            {messages.length === 0 && (
              <div className="flex flex-col items-center justify-center text-center dark:text-neutral-200 flex-1">
                {typeof window !== "undefined" && localStorage.getItem("userFirstName") ? (
                  <div>
                    <h1 className="text-4xl font-bold">
                      Hello, {localStorage.getItem("userFirstName")}
                    </h1>
                    <p className="text-sm mt-2">
                      I'm here to listen and support you. How are you feeling today?
                    </p>
                  </div>

                ) : (
                  <div>
                     <h1 className="text-4xl font-bold">
                      Welcome!
                  </h1>
                    <p className="text-sm mt-2">
                    Please log in to start the conversation.
                  </p>
                  </div>
                 
                )}

              </div>
            )}

            {/* Chat Bubbles */}
            {messages.map((message, index) => (
              <div
                key={index}
                className={`${message.sender === "user" ? "self-end" : "self-start"
                  } dark:bg-neutral-800 bg-gray-100 text-black dark:text-gray-100 px-4 py-2 rounded-lg max-w-[70%] break-words shadow-md`}
              >
                {message.text.split("\n").map((line, idx) => (
                  <div key={idx}>{line}</div>
                ))}
              </div>
            ))}

            {/* Loading Spinner */}
            {loading && (
              <div className="self-start dark:bg-neutral-800 bg-gray-100 text-black dark:text-gray-100 px-4 py-2 rounded-lg max-w-[70%] break-words shadow-md flex items-center gap-2">
                <Loader className="animate-spin" size={16} />
                <span>Bot is typing...</span>
              </div>
            )}

            {/* Scroll to this element */}
            <div ref={chatEndRef} />
          </div>
        </main>

        {/* Footer */}
        <footer className="sticky bottom-0 py-1 border-neutral-700">
          <div className="flex items-center gap-2 border rounded-xl p-3">
            {/* Left Button */}
            <Button
              variant="ghost"
              size="icon"
              className="text-gray-400 hover:text-white"
              disabled={loading} // Disable the mic button while loading
            >
              <Mic />
            </Button>

            {/* Multiline Textarea */}
            <Textarea
              ref={textareaRef}
              className="flex-1 min-w-[200px] h-[36px] bg-transparent border-none focus:ring-0 focus:outline-none resize-none overflow-hidden"
              placeholder="Type here..."
              value={inputValue} // Controlled value
              onChange={(e) => setInputValue(e.target.value)} // Update input state
              onKeyDown={(e) => {
                if (e.key === "Enter" && !e.shiftKey) {
                  e.preventDefault();
                  if (!loading) handleSendMessage(); // Send message on Enter key
                }
              }}
              rows={1}
              onInput={(e) => {
                e.currentTarget.style.height = "auto"; // Reset height to calculate scrollHeight properly
                e.currentTarget.style.height = `${e.currentTarget.scrollHeight}px`;
              }}
              disabled={loading} // Disable textarea while loading
            />

            {/* Right Button */}
            <Button
              variant="ghost"
              size="icon"
              className="text-gray-400 hover:text-white"
              onClick={handleSendMessage}
              disabled={loading} // Disable send button while loading
            >
              <SendHorizonal />
            </Button>
          </div>
        </footer>
      </div>
    </div>
  );
}
