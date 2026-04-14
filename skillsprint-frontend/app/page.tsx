"use client";

import { useState } from "react";
import ReactMarkdown from "react-markdown";

export default function Home() {
  const [targetCompany, setTargetCompany] = useState("");
  const [professionalStatus, setProfessionalStatus] = useState("");
  const [skills, setSkills] = useState("");

  const [blueprint, setBlueprint] = useState("");
  const [loading, setLoading] = useState(false);

  const generateBlueprint = async (e: React.FormEvent) => {
    e.preventDefault();
    setLoading(true);
    setBlueprint("");

    try {
      // This calls your Spring Boot backend!
      const response = await fetch("http://localhost:8080/api/v1/strategist/generate-blueprint", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          targetCompany,
          professionalStatus,
          skills,
        }),
      });

      if (!response.ok) {
        throw new Error("Failed to generate blueprint");
      }

      // We use .text() instead of .json() because your backend returns a raw String
      const data = await response.text();
      setBlueprint(data);
    } catch (error) {
      console.error("Error:", error);
      setBlueprint("Oops! Something went wrong connecting to the AI.");
    } finally {
      setLoading(false);
    }
  };

  return (
    <main className="min-h-screen p-8 bg-gray-50 font-sans text-gray-900">
      <div className="max-w-3xl mx-auto space-y-8">

        {/* Header */}
        <div className="text-center space-y-2">
          <h1 className="text-4xl font-bold text-blue-600">SkillSprint AI Strategist</h1>
          <p className="text-gray-600">Generate your personalized interview cracking blueprint.</p>
        </div>

        {/* The Input Form */}
        <form onSubmit={generateBlueprint} className="bg-white p-6 rounded-xl shadow-md space-y-4">
          <div>
            <label className="block text-sm font-medium mb-1">Target Company</label>
            <input
              type="text"
              className="w-full p-2 border rounded-md"
              placeholder="e.g. Google, Amazon, Stripe"
              value={targetCompany}
              onChange={(e) => setTargetCompany(e.target.value)}
              required
            />
          </div>

          <div>
            <label className="block text-sm font-medium mb-1">Your Professional Status</label>
            <input
              type="text"
              className="w-full p-2 border rounded-md"
              placeholder="e.g. 3rd Year CS Student, Junior Dev"
              value={professionalStatus}
              onChange={(e) => setProfessionalStatus(e.target.value)}
              required
            />
          </div>

          <div>
            <label className="block text-sm font-medium mb-1">Your Top Skills</label>
            <input
              type="text"
              className="w-full p-2 border rounded-md"
              placeholder="e.g. Java, Next.js, MySQL"
              value={skills}
              onChange={(e) => setSkills(e.target.value)}
              required
            />
          </div>

          <button
            type="submit"
            disabled={loading}
            className="w-full bg-blue-600 text-white font-bold py-3 rounded-md hover:bg-blue-700 disabled:bg-blue-300 transition-colors"
          >
            {loading ? "Analyzing Hiring Data..." : "Generate Blueprint"}
          </button>
        </form>

        {/* The AI Response Display */}
        {blueprint && (
          <div className="bg-white p-8 rounded-xl shadow-lg border-t-4 border-blue-600 prose prose-blue max-w-none">
            <ReactMarkdown>{blueprint}</ReactMarkdown>
          </div>
        )}

      </div>
    </main>
  );
}