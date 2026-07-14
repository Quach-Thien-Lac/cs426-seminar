interface ServiceResponsePayload {
	message: String,
	data?: any
}

interface ServiceResponse {
	success: Boolean,
	statusCode: number,
	payload: ServiceResponsePayload
}

export default ServiceResponse;