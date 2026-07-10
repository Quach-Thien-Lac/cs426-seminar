interface ServiceResponsePayload {
	message: String,
	data: Object | null
}

interface ServiceResponse {
	success: Boolean,
	statusCode: Number,
	payload: ServiceResponsePayload
}

export default ServiceResponse;