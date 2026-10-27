/**
 * TIFFZY STAFF WORKSPACE DESIGNATION THEME SYSTEM
 * 
 * Centralized design tokens and theme resolvers for staff designation workspaces.
 * Guarantees visual consistency across Chef, Senior Chef, Server, Steward, Cashier,
 * Cleaner, Swiper, Manager, and Custom Staff stations.
 */

export const STAFF_THEMES = {
  CHEF: {
    id: "CHEF",
    label: "Chef",
    activeTab: "bg-[#F97316] text-white shadow-xs border border-[#EA580C]",
    activeBadge: "bg-white text-[#EA580C] font-black",
    activeIcon: "text-white",
    inactiveTab: "bg-white text-[#57534E] border border-[#E7E5E4] hover:bg-[#FFF7ED] hover:text-[#EA580C]",
    inactiveBadge: "bg-[#F5F5F4] text-[#78716C] font-bold",
    inactiveIcon: "text-[#78716C]",
    pill: "bg-[#FFF7ED] text-[#EA580C] border border-[#F97316]/30",
    accentText: "text-[#EA580C]",
    accentBorder: "border-[#F97316]",
    accentBg: "bg-[#F97316]",
    accentHover: "hover:bg-[#EA580C]",
  },
  SENIOR_CHEF: {
    id: "SENIOR_CHEF",
    label: "Senior Chef",
    activeTab: "bg-[#F97316] text-white shadow-xs border border-[#EA580C]",
    activeBadge: "bg-white text-[#EA580C] font-black",
    activeIcon: "text-white",
    inactiveTab: "bg-white text-[#57534E] border border-[#E7E5E4] hover:bg-[#FFF7ED] hover:text-[#EA580C]",
    inactiveBadge: "bg-[#F5F5F4] text-[#78716C] font-bold",
    inactiveIcon: "text-[#78716C]",
    pill: "bg-[#FFF7ED] text-[#EA580C] border border-[#F97316]/30",
    accentText: "text-[#EA580C]",
    accentBorder: "border-[#F97316]",
    accentBg: "bg-[#F97316]",
    accentHover: "hover:bg-[#EA580C]",
  },
  SERVER: {
    id: "SERVER",
    label: "Server",
    activeTab: "bg-[#F97316] text-white shadow-xs border border-[#EA580C]",
    activeBadge: "bg-white text-[#EA580C] font-black",
    activeIcon: "text-white",
    inactiveTab: "bg-white text-[#57534E] border border-[#E7E5E4] hover:bg-[#FFF7ED] hover:text-[#EA580C]",
    inactiveBadge: "bg-[#F5F5F4] text-[#78716C] font-bold",
    inactiveIcon: "text-[#78716C]",
    pill: "bg-[#FFF7ED] text-[#EA580C] border border-[#F97316]/30",
    accentText: "text-[#EA580C]",
    accentBorder: "border-[#F97316]",
    accentBg: "bg-[#F97316]",
    accentHover: "hover:bg-[#EA580C]",
  },
  STEWARD: {
    id: "STEWARD",
    label: "Steward",
    activeTab: "bg-[#F97316] text-white shadow-xs border border-[#EA580C]",
    activeBadge: "bg-white text-[#EA580C] font-black",
    activeIcon: "text-white",
    inactiveTab: "bg-white text-[#57534E] border border-[#E7E5E4] hover:bg-[#FFF7ED] hover:text-[#EA580C]",
    inactiveBadge: "bg-[#F5F5F4] text-[#78716C] font-bold",
    inactiveIcon: "text-[#78716C]",
    pill: "bg-[#FFF7ED] text-[#EA580C] border border-[#F97316]/30",
    accentText: "text-[#EA580C]",
    accentBorder: "border-[#F97316]",
    accentBg: "bg-[#F97316]",
    accentHover: "hover:bg-[#EA580C]",
  },
  CASHIER: {
    id: "CASHIER",
    label: "Cashier",
    activeTab: "bg-[#F97316] text-white shadow-xs border border-[#EA580C]",
    activeBadge: "bg-white text-[#EA580C] font-black",
    activeIcon: "text-white",
    inactiveTab: "bg-white text-[#57534E] border border-[#E7E5E4] hover:bg-[#FFF7ED] hover:text-[#EA580C]",
    inactiveBadge: "bg-[#F5F5F4] text-[#78716C] font-bold",
    inactiveIcon: "text-[#78716C]",
    pill: "bg-[#ECFDF5] text-[#059669] border border-[#059669]/30",
    accentText: "text-[#059669]",
    accentBorder: "border-[#059669]",
    accentBg: "bg-[#059669]",
    accentHover: "hover:bg-[#047857]",
  },
  CLEANER: {
    id: "CLEANER",
    label: "Cleaner",
    activeTab: "bg-[#F97316] text-white shadow-xs border border-[#EA580C]",
    activeBadge: "bg-white text-[#EA580C] font-black",
    activeIcon: "text-white",
    inactiveTab: "bg-white text-[#57534E] border border-[#E7E5E4] hover:bg-[#FFF7ED] hover:text-[#EA580C]",
    inactiveBadge: "bg-[#F5F5F4] text-[#78716C] font-bold",
    inactiveIcon: "text-[#78716C]",
    pill: "bg-[#FFFBEB] text-[#D97706] border border-[#D97706]/30",
    accentText: "text-[#D97706]",
    accentBorder: "border-[#D97706]",
    accentBg: "bg-[#F97316]",
    accentHover: "hover:bg-[#EA580C]",
  },
  SWIPER: {
    id: "SWIPER",
    label: "Swiper",
    activeTab: "bg-[#F97316] text-white shadow-xs border border-[#EA580C]",
    activeBadge: "bg-white text-[#EA580C] font-black",
    activeIcon: "text-white",
    inactiveTab: "bg-white text-[#57534E] border border-[#E7E5E4] hover:bg-[#FFF7ED] hover:text-[#EA580C]",
    inactiveBadge: "bg-[#F5F5F4] text-[#78716C] font-bold",
    inactiveIcon: "text-[#78716C]",
    pill: "bg-[#FFF7ED] text-[#EA580C] border border-[#F97316]/30",
    accentText: "text-[#EA580C]",
    accentBorder: "border-[#F97316]",
    accentBg: "bg-[#F97316]",
    accentHover: "hover:bg-[#EA580C]",
  },
  MANAGER: {
    id: "MANAGER",
    label: "Manager",
    activeTab: "bg-[#F97316] text-white shadow-xs border border-[#EA580C]",
    activeBadge: "bg-white text-[#EA580C] font-black",
    activeIcon: "text-white",
    inactiveTab: "bg-white text-[#57534E] border border-[#E7E5E4] hover:bg-[#FFF7ED] hover:text-[#EA580C]",
    inactiveBadge: "bg-[#F5F5F4] text-[#78716C] font-bold",
    inactiveIcon: "text-[#78716C]",
    pill: "bg-[#FFF7ED] text-[#EA580C] border border-[#F97316]/30",
    accentText: "text-[#EA580C]",
    accentBorder: "border-[#F97316]",
    accentBg: "bg-[#F97316]",
    accentHover: "hover:bg-[#EA580C]",
  },
  STAFF: {
    id: "STAFF",
    label: "Staff",
    activeTab: "bg-[#F97316] text-white shadow-xs border border-[#EA580C]",
    activeBadge: "bg-white text-[#EA580C] font-black",
    activeIcon: "text-white",
    inactiveTab: "bg-white text-[#57534E] border border-[#E7E5E4] hover:bg-[#FFF7ED] hover:text-[#EA580C]",
    inactiveBadge: "bg-[#F5F5F4] text-[#78716C] font-bold",
    inactiveIcon: "text-[#78716C]",
    pill: "bg-[#FFF7ED] text-[#EA580C] border border-[#F97316]/30",
    accentText: "text-[#EA580C]",
    accentBorder: "border-[#F97316]",
    accentBg: "bg-[#F97316]",
    accentHover: "hover:bg-[#EA580C]",
  },
};

/**
 * Resolves a designation theme from designation or role string
 * @param {string} designation - Staff designation or role
 * @param {string} [role] - Optional staff role fallback
 * @returns {typeof STAFF_THEMES.CHEF} Theme configuration
 */
export const getDesignationTheme = (designation, role = "") => {
  const text = String(designation || role || "").trim().toUpperCase();

  if (text.includes("SENIOR") || text.includes("HEAD CHEF") || text.includes("EXECUTIVE") || text.includes("LEAD CHEF") || text.includes("SOUS")) {
    return STAFF_THEMES.SENIOR_CHEF;
  }
  if (text.includes("CHEF")) {
    return STAFF_THEMES.CHEF;
  }
  if (text.includes("SERVER") || text.includes("WAITER")) {
    return STAFF_THEMES.SERVER;
  }
  if (text.includes("STEWARD") || text.includes("RUNNER")) {
    return STAFF_THEMES.STEWARD;
  }
  if (text.includes("CASHIER") || text.includes("BILLING") || text.includes("POS")) {
    return STAFF_THEMES.CASHIER;
  }
  if (text.includes("CLEANER") || text.includes("SANITATION") || text.includes("BUSSER")) {
    return STAFF_THEMES.CLEANER;
  }
  if (text.includes("SWIPER")) {
    return STAFF_THEMES.SWIPER;
  }
  if (text.includes("MANAGER") || text.includes("OWNER") || text.includes("ADMIN")) {
    return STAFF_THEMES.MANAGER;
  }

  return STAFF_THEMES.STAFF;
};
