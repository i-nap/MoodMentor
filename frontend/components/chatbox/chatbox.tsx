import React, { useState, useRef, useEffect } from "react";
import { Mic, SendHorizonal } from "lucide-react";
import { Button } from "../ui/button";
import { Textarea } from "../ui/textarea";

export default function ChatBox() {
  const [messages, setMessages] = useState([]); // State to store chat messages
  const [inputValue, setInputValue] = useState(""); // State for the textarea value
  const chatEndRef = useRef(null); // Ref to scroll to the bottom of the chat
  const textareaRef = useRef(null); // Ref to reset textarea height

  const handleSendMessage = () => {
    if (inputValue.trim()) {
      setMessages((prevMessages) => [...prevMessages, inputValue]); // Add new message to the list
      setInputValue(""); // Clear the textarea
      resetTextareaHeight(); // Reset the height of the textarea

    }
  };

  const resetTextareaHeight = () => {
    if (textareaRef.current) {
      textareaRef.current.style.height = "36px"; // Reset to the default height
    }
  };

  // Scroll to the bottom of the chat when a new message is added
  useEffect(() => {
    chatEndRef.current?.scrollIntoView({ behavior: "smooth" });
  }, [messages]);

  return (
<div className="flex flex-1">
      <div className="px-2 md:py-10 md:px-60 rounded-tl-2xl border border-neutral-200 dark:border-neutral-700 bg-white dark:bg-neutral-900 flex flex-col flex-1 w-full h-full">
      {/* Chat Area */}
      <main className="flex-1 overflow-y-auto  py-6 flex items-center justify-center">
      <div className="flex flex-col gap-3 w-full">
        {/* Introductory Text */}
        {messages.length === 0 && (
          <div className="flex flex-col items-center justify-center text-center dark:text-neutral-200">
            <h1 className="text-4xl font-bold">Hello, Username</h1>
            <p className="text-sm mt-2">
              I'm here to listen and support you. How are you feeling today?
            </p>
          </div>
        )}

          {/* Chat Bubbles */}
          {messages.map((message, index) => (
            <div
              key={index}
              className="self-end dark:bg-neutral-800 bg-gray-100 text-black dark:text-gray-100 px-4 py-2 rounded-lg max-w-[70%] break-words shadow-md"
            >
              {message}
            </div>
          ))}
          {/* Scroll to this element */}
          <div ref={chatEndRef} />
        </div>
      </main>

      {/* Footer */}
      <footer className="sticky bottom-0 py-1   border-neutral-700">
        <div className="flex items-center gap-2 border rounded-xl p-3">
          {/* Left Button */}
          <Button
            variant="ghost"
            size="icon"
            className="text-gray-400 hover:text-white"
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
                handleSendMessage(); // Send message on Enter key
              }
            }}
            rows={1}
            onInput={(e) => {
              e.currentTarget.style.height = "auto"; // Reset height to calculate scrollHeight properly
              e.currentTarget.style.height = `${e.currentTarget.scrollHeight}px`;
            }}
          />

          {/* Right Button */}
          <Button
            variant="ghost"
            size="icon"
            className="text-gray-400 hover:text-white"
            onClick={handleSendMessage}
          >
            <SendHorizonal />
          </Button>
        </div>
      </footer>
    </div>
    </div>
  );
}
